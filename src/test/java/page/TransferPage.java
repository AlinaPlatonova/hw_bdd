package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    // поле суммы
    private SelenideElement amountField = $("[data-test-id='amount'] input");

    // поле "Откуда"
    private SelenideElement fromCardField = $("[data-test-id='from'] input");

    // поле "Куда"
    //private SelenideElement toCardField = $("[data-test-id='to']");

    // кнопка "Пополнить"
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    // кнопка "Отмена"
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    // проверяем что мы на странице перевода
    public TransferPage() {
        amountField.shouldBe(Condition.visible);
    }

    public void setFromCard(String cardNumber) {
        String cleanNumber = cardNumber.replace(" ", "");
        fromCardField.setValue(cleanNumber);
    }


    // вводим сумму в поле "Сумма"
    public void setAmount(int amount) {
        amountField.setValue(String.valueOf(amount));
    }


    // нажимаем кнопку "пополнить"
    public void clickTransferButton() {
        transferButton.click();
    }

    // метод для нажатия кнопки "Отмена"
    public void clickCancelButton() {
        cancelButton.click();
    }

    // передаем полный номер карты
    public void makeTransfer(int amount, String fromCardNumber) {
        setAmount(amount);
        setFromCard(fromCardNumber);
        clickTransferButton();
    }
}

