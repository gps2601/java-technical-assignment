package kata.supermarket.product;

import java.math.BigDecimal;

public class ItemByUnit implements Item {
    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    @Override
    public ProductName name() {
        return product.getProductName();
    }
}
