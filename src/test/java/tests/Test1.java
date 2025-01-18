package tests;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.CardItems;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;

public class Test1 {

    @BeforeAll
    public static void setup() {
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void newTest() {
        open("https://www.saucedemo.com/");

        // Создаем объект для авторизации
        LoginPage loginPage = new LoginPage();

        try{
            // Идет проверка на существование ui элементов
            loginPage.verifyElementsAreVisible();

            // Передаем данные для входа
            loginPage.login("standard_user", "secret_sauce");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        try {
            // Инициализация объекта с карточкой товара
            CardItems cardItems =  new CardItems();

            // Получаем коллекцию карточек
            // По условию добавляем картоки в корзмну
            cardItems.addItemsFromCollectionToCart();

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}