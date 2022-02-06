package kata.supermarket.pricing;

import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public interface Discounter {
    BigDecimal calculateDiscounts(List<Item> items);
}
