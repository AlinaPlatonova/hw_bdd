package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    // получение информации о первой карте
    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    // получение информации о второй карте
    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class AuthInfo {
        String login;    // логин
        String password; // пароль
    }

    @Value
    public static class VerificationCode {
        String code; // код верификации
    }

    @Value
    public static class CardInfo {
        String cardNumber; // полный номер карты
        String testId;     // тестовая метка карты
    }

}
