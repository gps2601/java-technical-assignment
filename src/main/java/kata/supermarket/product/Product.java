package kata.supermarket.product;

import java.math.BigDecimal;

import static kata.supermarket.product.ProductName.UNKNOWN;

public class Product {
    private final BigDecimal pricePerUnit;
    private final ProductName productName;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.productName = UNKNOWN;
    }

    public Product(BigDecimal pricePerUnit, ProductName productName) {
        this.pricePerUnit = pricePerUnit;
        this.productName = productName;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public ProductName getProductName() {
        return productName;
    }
}
