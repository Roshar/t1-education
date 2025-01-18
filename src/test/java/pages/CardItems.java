package pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class CardItems {

    private final ElementsCollection cards = $$(".inventory_item");
    private static final double PRICE_LIMIT = 20.0;
    private final By cartBtn = By.xpath(".//button[contains(text(), 'Add to cart')]");
    private final By priceForItem = By.xpath(".//*[@data-test='inventory-item-price']");
    private final SelenideElement addCartBtn = $x("//*[@data-test='shopping-cart-link']");

    public void addItemsFromCollectionToCart() {

        var counter = 0; // Счетчик итераций

        for (var i = 0; i < cards.size(); i++) {

            SelenideElement card = cards.get(i); // Получаем текущую карточку
            counter++; // Увеличиваем счетчик на каждой итерации для отладки завел

            // Получаем цену товара из карточки и парсим цену
            String priceElement = card.$(priceForItem).getText();
            System.out.println("Текст элемента цены: " + priceElement);

            var price = Double.parseDouble(priceElement.replace("$", ""));
            System.out.println("Цена товара: " + price);

            // Проверяем условие цены
            if (price < PRICE_LIMIT) {
                System.out.println("Товар с ценой больше " + PRICE_LIMIT + " найден на итерации #" + counter);
                // Находим кнопку "Add to cart" и кликаем по ней
                card.$(cartBtn)
                        .shouldBe(Condition.visible.because("Кнопка 'Добавить в корзину' не найдена"))
                        .click();
            } else {
                System.out.println("Товар с ценой меньше " + PRICE_LIMIT + " пропущен на итерации #" + counter);
            }
        }

        // Выводим общее количество обработанных карточек
        addCartBtn.shouldBe(Condition.visible.because("Кнопка корзины не найдена")).click();
    }

}
