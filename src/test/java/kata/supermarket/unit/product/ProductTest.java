package kata.supermarket.unit.product;

import kata.supermarket.product.Product;
import kata.supermarket.product.ProductName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static kata.supermarket.product.ProductName.COOKIES;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal expectedPrice = new BigDecimal("2.49");

        BigDecimal actualPrice = new Product(expectedPrice).oneOf().price();

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void unitProductsCanReturnTheirName() {
        ProductName expectedName = COOKIES;
        final BigDecimal price = new BigDecimal("2.49");
        Product product = new Product(price, expectedName);

        ProductName actualName = product.oneOf().name();

        assertEquals(expectedName, actualName);
    }
}
