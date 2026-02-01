package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");

    private SelenideElement passwordField = $("[data-test-id='password'] input");

    private SelenideElement continueButton = $("[data-test-id='action-login']");

    public void setLogin(String login) {
        loginField.setValue(login); // вводим логин в поле
    }

    public void setPassword(String password) {
        passwordField.setValue(password); // вводим пароль в поле
    }

    public void clickContinue() {
        continueButton.click(); // Кликаем по кнопке
    }

    // выполняем вход
    public void validLogin(DataHelper.AuthInfo authInfo) {
        setLogin(authInfo.getLogin());     // вводим логин
        setPassword(authInfo.getPassword()); // вводим пароль
        clickContinue();                   // нажимаем кнопку
    }
}
