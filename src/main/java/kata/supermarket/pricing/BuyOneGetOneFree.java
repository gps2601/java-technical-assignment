package kata.supermarket.pricing;

import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public class BuyOneGetOneFree implements Discount {
    @Override
    public BigDecimal discountFor(List<Item> items) {
        return null;
    }
}
