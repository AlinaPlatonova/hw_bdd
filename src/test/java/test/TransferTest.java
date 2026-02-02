package test;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.TransferPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        // получаем данные для входа
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        // получаем код подтверждения
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        // получаем информацию о картах
        DataHelper.CardInfo firstCardInfo = DataHelper.getFirstCardInfo();
        DataHelper.CardInfo secondCardInfo = DataHelper.getSecondCardInfo();

        // логинимся
        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(authInfo);

        // вводим код подтверждения
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validVerify(verificationCode);

        // проверяем что мы на странице с картами
        DashboardPage dashboardPage = new DashboardPage();

        // получаем балансы карт до перевода
        int firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo);
        int secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo);

        // выбираем первую карту для пополнения
        TransferPage transferPage = dashboardPage.selectCardToReplenish(firstCardInfo);


        // переводим 500 рублей с второй карты на первую
        int transferAmount = 500;

        // берем полный номер второй карты
        String secondCardFullNumber = secondCardInfo.getCardNumber();
        transferPage.makeTransfer(transferAmount, secondCardFullNumber);

        // после перевода снова открываем страницу с картами
        DashboardPage dashboardPageAfter = new DashboardPage();

        // получаем балансы карт после перевода
        int firstCardEndBalance = dashboardPageAfter.getCardBalance(firstCardInfo);
        int secondCardEndBalance = dashboardPageAfter.getCardBalance(secondCardInfo);


        // проверяем что баланс первой карты увеличился на 500
        assertEquals(firstCardStartBalance + transferAmount, firstCardEndBalance,
                "Баланс первой карты должен быть: " + (firstCardStartBalance + transferAmount) +
                        ", но получили: " + firstCardEndBalance);

        // проверяем что баланс второй карты уменьшился на 500
        assertEquals(secondCardStartBalance - transferAmount, secondCardEndBalance,
                "Баланс второй карты должен быть: " + (secondCardStartBalance - transferAmount) +
                        ", но получили: " + secondCardEndBalance);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        // получаем данные для входа
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        // получаем код подтверждения
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        // получаем информацию о картах
        DataHelper.CardInfo firstCardInfo = DataHelper.getFirstCardInfo();
        DataHelper.CardInfo secondCardInfo = DataHelper.getSecondCardInfo();

        // логинимся
        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(authInfo);

        // вводим код подтверждения
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validVerify(verificationCode);

        // проверяем что мы на странице с картами
        DashboardPage dashboardPage = new DashboardPage();

        // получаем балансы карт до перевода
        int firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo);
        int secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo);

        // выбираем вторую карту для пополнения
        TransferPage transferPage = dashboardPage.selectCardToReplenish(secondCardInfo);



        // переводим 1000 рублей с первой карты на вторую
        int transferAmount = 1000;

        // берем полный номер первой карты
        String firstCardFullNumber = firstCardInfo.getCardNumber();
        transferPage.makeTransfer(transferAmount, firstCardFullNumber);

        // после перевода снова открываем страницу с картами
        DashboardPage dashboardPageAfter = new DashboardPage();

        // получаем балансы карт после перевода
        int firstCardEndBalance = dashboardPageAfter.getCardBalance(firstCardInfo);
        int secondCardEndBalance = dashboardPageAfter.getCardBalance(secondCardInfo);

        // проверяем, что баланс второй карты увеличился на 1000
        assertEquals(secondCardStartBalance + transferAmount, secondCardEndBalance,
                "Баланс второй карты должен быть: " + (secondCardStartBalance + transferAmount) +
                        ", но получили: " + secondCardEndBalance);

        // проверяем, что баланс первой карты уменьшился на 1000
        assertEquals(firstCardStartBalance - transferAmount, firstCardEndBalance,
                "Баланс первой карты должен быть: " + (firstCardStartBalance - transferAmount) +
                        ", но получили: " + firstCardEndBalance);
    }

}