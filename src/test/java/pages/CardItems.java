package pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class CardItems {

    private ElementsCollection cards = $$(".inventory_item");
    private static final double priceLimit = 20.0;
    private By cartBtn = By.xpath(".//button[contains(text(), 'Add to cart')]");
    private By priceForItem = By.xpath(".//*[@data-test='inventory-item-price']");
    private SelenideElement addCartBtn = $x("//*[@data-test='shopping-cart-link']");

    public void addItemsFromCollectionToCart() {

        int counter = 0; // Счетчик итераций

        for (int i = 0; i < cards.size(); i++) {

            SelenideElement card = cards.get(i); // Получаем текущую карточку
            counter++; // Увеличиваем счетчик на каждой итерации для отладки завел

            // Получаем цену товара из карточки и парсим цену
            String priceElement = card.$(priceForItem).getText();
            System.out.println("Текст элемента цены: " + priceElement);

            double price = Double.parseDouble(priceElement.replace("$", ""));
            System.out.println("Цена товара: " + price);

            // Проверяем условие цены
            if (price < priceLimit) {
                System.out.println("Товар с ценой больше " + priceLimit + " найден на итерации #" + counter);
                // Находим кнопку "Add to cart" и кликаем по ней
                SelenideElement addToCartButton = card.$(cartBtn)
                        .shouldBe(Condition.visible.because("Кнопка 'Добавить в корзину' не найдена"));
                addToCartButton.click();
            } else {
                System.out.println("Товар с ценой меньше " + priceLimit + " пропущен на итерации #" + counter);
            }
        }

        // Выводим общее количество обработанных карточек
        System.out.println("Всего обработано карточек: " + counter);
        addCartBtn.shouldBe(Condition.visible.because("Кнопка корзины не найдена")).click();
    }

}
