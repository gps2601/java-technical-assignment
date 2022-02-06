package kata.supermarket.integration;

import kata.supermarket.basket.Basket;
import kata.supermarket.pricing.BuyOneGetOneFree;
import kata.supermarket.pricing.SupermarketDiscounter;
import kata.supermarket.pricing.SupermarketTeller;
import kata.supermarket.product.Item;
import kata.supermarket.product.Product;
import kata.supermarket.product.ProductName;
import kata.supermarket.product.WeighedProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static kata.supermarket.product.ProductName.COOKIES;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketIntegrationTest {

    @DisplayName("basket provides its total value when containing:")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(@SuppressWarnings("unused") String description, String expectedTotal, Iterable<Item> items) {
        BigDecimal expected = new BigDecimal(expectedTotal);
        final Basket basket = new Basket(new SupermarketTeller(new SupermarketDiscounter()));
        items.forEach(basket::add);

        BigDecimal actual = basket.total();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleBuyOneGetOneFreeDiscounts() {
        BigDecimal expected = new BigDecimal("1.00");
        SupermarketDiscounter discounter = new SupermarketDiscounter();
        discounter.addDiscount(COOKIES, new BuyOneGetOneFree());
        SupermarketTeller teller = new SupermarketTeller(discounter);
        Basket basket = new Basket(teller);
        List<Item> items = asList(
                unitItem("1.00", COOKIES),
                unitItem("1.00", COOKIES));

        items.forEach(basket::add);

        assertEquals(expected, basket.total());
    }

    @Test
    void shouldHandleBuyOneGetOneFreeDiscountsForThreeItems() {
        BigDecimal expected = new BigDecimal("2.00");
        SupermarketDiscounter discounter = new SupermarketDiscounter();
        discounter.addDiscount(COOKIES, new BuyOneGetOneFree());
        SupermarketTeller teller = new SupermarketTeller(discounter);
        Basket basket = new Basket(teller);
        List<Item> items = asList(
                unitItem("1.00", COOKIES),
                unitItem("1.00", COOKIES),
                unitItem("1.00", COOKIES));

        items.forEach(basket::add);

        assertEquals(expected, basket.total());
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of(
                "a single item priced per unit", "0.49",
                singleton(unitItem("0.49"))
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of(
                "multiple items priced per unit",
                "2.04",
                asList(unitItem("1.55"), unitItem("0.49"))
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of(
                "a single weighed item",
                "1.25",
                singleton(weightItem("4.99", ".25"))
        );
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of(
                "multiple weighed items",
                "1.85",
                asList(weightItem("4.99", ".25"), weightItem("2.99", ".2"))
        );
    }

    private static Item unitItem(String price) {
        return new Product(new BigDecimal(price)).oneOf();
    }

    private Item unitItem(String price, ProductName name) {
        return new Product(new BigDecimal(price), name).oneOf();
    }

    private static Item weightItem(String price, String weight) {
        return new WeighedProduct(new BigDecimal(price)).weighing(new BigDecimal(weight));
    }
}