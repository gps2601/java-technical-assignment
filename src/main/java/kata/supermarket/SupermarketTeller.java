package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SupermarketTeller implements Teller {

    @Override
    public BigDecimal calculate(List<Item> items) {
        return subtotal(items).subtract(discounts());
    }

    private BigDecimal subtotal(List<Item> items) {
        return items.stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal discounts() {
        return BigDecimal.ZERO;
    }
}
