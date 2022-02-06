package kata.supermarket.pricing;

import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SupermarketTeller implements Teller {
    private final Discounter discounter;

    public SupermarketTeller(Discounter discounter) {
        this.discounter = discounter;
    }

    @Override
    public BigDecimal calculate(List<Item> items) {
        return subtotal(items).subtract(discounter.calculateDiscounts(items));
    }

    private BigDecimal subtotal(List<Item> items) {
        return items.stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
