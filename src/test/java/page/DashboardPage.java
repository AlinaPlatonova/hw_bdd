package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final SelenideElement header = $("[data-test-id=dashboard]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        header.shouldBe(Condition.visible);
    }

    private SelenideElement getCardElement(DataHelper.CardInfo cardInfo) {
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    // получить баланс карты
    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        SelenideElement cardElement = getCardElement(cardInfo);
        String cardText = cardElement.getText();
        return extractBalance(cardText);
    }

    // выбрать карту для пополнения
    public TransferPage selectCardToReplenish(DataHelper.CardInfo cardInfo) {
        SelenideElement cardElement = getCardElement(cardInfo);
        cardElement.$("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    // получение баланса карт
    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        value = value.replace(" ", "");
        return Integer.parseInt(value);
    }
}





