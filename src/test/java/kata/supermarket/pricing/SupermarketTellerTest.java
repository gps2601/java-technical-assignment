package kata.supermarket.pricing;

import kata.supermarket.product.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class SupermarketTellerTest {
    private final SupermarketTeller supermarketTeller = new SupermarketTeller();

    @Test
    void shouldHandleEmptyList() {
        BigDecimal expected = new BigDecimal("0.00");

        BigDecimal actual = supermarketTeller.calculate(emptyList());

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleSingleItems() {
        BigDecimal expected = new BigDecimal("1.01");
        Item item = () -> new BigDecimal("1.01");

        BigDecimal actual = supermarketTeller.calculate(singletonList(item));

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleMultipleItems() {
        BigDecimal expected = new BigDecimal("2.00");
        Item item1 = () -> new BigDecimal("1.01");
        Item item2 = () -> new BigDecimal(".99");

        BigDecimal actual = supermarketTeller.calculate(asList(item1, item2));

        assertEquals(expected, actual);
    }
}