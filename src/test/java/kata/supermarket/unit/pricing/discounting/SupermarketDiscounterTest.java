package kata.supermarket.unit.pricing.discounting;

import kata.supermarket.pricing.discounting.Discount;
import kata.supermarket.pricing.discounting.SupermarketDiscounter;
import kata.supermarket.product.Item;
import kata.supermarket.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static kata.supermarket.product.ProductName.COOKIES;
import static kata.supermarket.product.ProductName.UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketDiscounterTest {

    @Test
    void shouldHandleNoDiscounts() {
        SupermarketDiscounter supermarketDiscounter = new SupermarketDiscounter();
        BigDecimal expectedPrice = new BigDecimal("1.00");
        Item item = new Product(expectedPrice, UNKNOWN).oneOf();

        BigDecimal actualDiscount = supermarketDiscounter.calculateDiscounts(singletonList(item));

        assertEquals(ZERO, actualDiscount);
    }

    @Test
    void shouldHandleWhenThereIsADiscountForProduct() {
        BigDecimal price = new BigDecimal("1.00");
        SupermarketDiscounter supermarketDiscounter = new SupermarketDiscounter();
        Discount discountMock = items -> new BigDecimal("0.50");
        supermarketDiscounter.addDiscount(COOKIES, discountMock);
        Item item = new Product(price, COOKIES).oneOf();

        BigDecimal actualDiscount = supermarketDiscounter.calculateDiscounts(singletonList(item));

        assertEquals(new BigDecimal("0.50"), actualDiscount);
    }

    @Test
    void shouldHandleWhenThereIsMultipleDiscountsAvailable() {
        BigDecimal price = new BigDecimal("1.00");
        SupermarketDiscounter supermarketDiscounter = new SupermarketDiscounter();
        Discount firstDiscount = items -> new BigDecimal("0.50");
        supermarketDiscounter.addDiscount(COOKIES, firstDiscount);
        Discount secondDiscount = Items -> new BigDecimal("0.30");
        supermarketDiscounter.addDiscount(UNKNOWN, secondDiscount);

        Item cookie = new Product(price, COOKIES).oneOf();
        Item unknownItem = new Product(price, UNKNOWN).oneOf();

        BigDecimal actualDiscount = supermarketDiscounter.calculateDiscounts(asList(cookie, unknownItem));

        assertEquals(new BigDecimal(".80"), actualDiscount);
    }
}