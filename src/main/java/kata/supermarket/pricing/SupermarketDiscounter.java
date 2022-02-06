package kata.supermarket.pricing;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class SupermarketDiscounter implements Discounter {
    @Override
    public BigDecimal calculateDiscounts(List<Item> items) {
        return ZERO;
    }

    public void addDiscount(ProductName name, Discount discount) {

    }
}
