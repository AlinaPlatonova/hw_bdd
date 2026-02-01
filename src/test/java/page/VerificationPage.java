package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");

    private SelenideElement continueButton = $("[data-test-id='action-verify']");

    public void setCode(String code) {
        codeField.setValue(code); // вводим код в поле
    }

    public void clickContinue() {
        continueButton.click(); // кликаем по кнопке
    }

    // подтверждение кода
    public void validVerify(DataHelper.VerificationCode verificationCode) {
        setCode(verificationCode.getCode()); // вводим код
        clickContinue();                     // нажимаем кнопку
    }
}
