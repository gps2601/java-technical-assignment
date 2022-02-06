package kata.supermarket.unit.product;

import kata.supermarket.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal expectedPrice = new BigDecimal("2.49");

        BigDecimal actualPrice = new Product(expectedPrice).oneOf().price();

        assertEquals(expectedPrice, actualPrice);
    }
}