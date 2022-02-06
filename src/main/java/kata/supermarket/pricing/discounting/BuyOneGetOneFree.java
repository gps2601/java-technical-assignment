package kata.supermarket.pricing.discounting;

import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

public class BuyOneGetOneFree implements Discount {

    @Override
    public BigDecimal discountFor(List<Item> items) {
        BigDecimal eachItemPrice = items.stream().findAny().get().price();
        int numberOfItems = items.size();
        if (numberOfItems > 1) {
            return eachItemPrice.multiply(valueOf(numberOfItems / 2));
        }
        return ZERO;
    }
}
