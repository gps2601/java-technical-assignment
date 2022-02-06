package kata.supermarket.unit.pricing;

import kata.supermarket.pricing.SupermarketTeller;
import kata.supermarket.product.Item;
import kata.supermarket.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketTellerTest {
    private final SupermarketTeller supermarketTeller = new SupermarketTeller(items -> ZERO);

    @Test
    void shouldHandleEmptyList() {
        BigDecimal expected = new BigDecimal("0.00");

        BigDecimal actual = supermarketTeller.calculate(emptyList());

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleSingleItems() {
        BigDecimal expected = new BigDecimal("1.01");
        Item item = new Product(expected).oneOf();

        BigDecimal actual = supermarketTeller.calculate(singletonList(item));

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleMultipleItems() {
        BigDecimal expected = new BigDecimal("2.00");
        Item item1 = new Product(new BigDecimal("1.01")).oneOf();
        Item item2 = new Product(new BigDecimal(".99")).oneOf();

        BigDecimal actual = supermarketTeller.calculate(asList(item1, item2));

        assertEquals(expected, actual);
    }

    @Test
    void shouldDeductDiscountsCalculatedByDiscounter() {
        BigDecimal expected = new BigDecimal("1.00");
        SupermarketTeller supermarketTeller = new SupermarketTeller(items -> new BigDecimal(".50"));
        Item item = new Product(new BigDecimal("1.50")).oneOf();

        BigDecimal actual = supermarketTeller.calculate(singletonList(item));

        assertEquals(expected, actual);
    }
}