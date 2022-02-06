package kata.supermarket.unit.pricing.discounting;

import kata.supermarket.pricing.discounting.BuyOneGetOneFree;
import kata.supermarket.product.Item;
import kata.supermarket.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class BuyOneGetOneFreeTest {

    @Test
    void shouldReturnZeroDiscountWhenOnlyOneItem() {
        BuyOneGetOneFree discount = new BuyOneGetOneFree();
        Item item = new Product(new BigDecimal("1.00")).oneOf();

        BigDecimal actual = discount.discountFor(Collections.singletonList(item));

        assertEquals(ZERO, actual);
    }

    @Test
    void shouldReturnCorrectDiscountAmountForTwoItems() {
        BigDecimal expected = new BigDecimal("1.00");
        BuyOneGetOneFree discount = new BuyOneGetOneFree();
        Item item1 = new Product(new BigDecimal("1.00")).oneOf();
        Item item2 = new Product(new BigDecimal("1.00")).oneOf();

        BigDecimal actual = discount.discountFor(asList(item1, item2));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectDiscountAmountForThreeItems() {
        BigDecimal expected = new BigDecimal("1.00");
        BuyOneGetOneFree discount = new BuyOneGetOneFree();
        Item item1 = new Product(new BigDecimal("1.00")).oneOf();
        Item item2 = new Product(new BigDecimal("1.00")).oneOf();
        Item item3 = new Product(new BigDecimal("1.00")).oneOf();

        BigDecimal actual = discount.discountFor(asList(item1, item2, item3));

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectDiscountAmountFor5Items() {
        BigDecimal expected = new BigDecimal("2.00");
        BuyOneGetOneFree discount = new BuyOneGetOneFree();
        Item item1 = new Product(new BigDecimal("1.00")).oneOf();
        Item item2 = new Product(new BigDecimal("1.00")).oneOf();
        Item item3 = new Product(new BigDecimal("1.00")).oneOf();
        Item item4 = new Product(new BigDecimal("1.00")).oneOf();
        Item item5 = new Product(new BigDecimal("1.00")).oneOf();

        BigDecimal actual = discount.discountFor(asList(item1, item2, item3, item4, item5));

        assertEquals(expected, actual);
    }
}