package kata.supermarket.pricing.discounting;

import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public interface Discount {
    BigDecimal discountFor(List<Item> items);
}
