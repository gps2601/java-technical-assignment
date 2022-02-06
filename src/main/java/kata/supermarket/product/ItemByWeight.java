package kata.supermarket.product;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static kata.supermarket.product.ProductName.UNKNOWN;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }

    @Override
    public ProductName name() {
        return UNKNOWN;
    }
}
