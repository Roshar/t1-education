package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement usernameField = $x("//input[@id='user-name']");
    private SelenideElement passwordField = $x("//input[@id='password']");
    private SelenideElement loginBtn = $x("//input[@id='login-button']");

    // заполняем поля для логина  и пароля
    public void enterLoginData(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
    }

    // реализуем кнопку входа
    public void clickLoginButton() {
        loginBtn.click();
    }

    // Авторизация по логину и паролю
    public void login(String username, String password) {
        enterLoginData(username, password);
        clickLoginButton();
    }

    // Проверки по присутствию элементов на странице
    public void verifyElementsAreVisible() {
        usernameField.shouldBe(Condition.visible.because("Поле ввода имени пользователя не отображается"));
        passwordField.shouldBe(Condition.visible.because("Поле ввода пароля пользователя не отображается"));
        loginBtn.shouldBe(Condition.visible.because("Кнопка войти не отображается"));
    }

}
