package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class TotalCalculator {
    private final Basket basket;
    private final List<Item> items;

    TotalCalculator(Basket basket) {
        this.basket = basket;
        this.items = basket.items();
    }

    public BigDecimal calculate() {
        return subtotal().subtract(discounts());
    }

    private BigDecimal subtotal() {
        return items.stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal discounts() {
        return BigDecimal.ZERO;
    }
}
