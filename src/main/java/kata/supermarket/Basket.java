package kata.supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Basket {
    private final List<Item> items;
    private final TotalCalculator totalCalculator;

    public Basket() {
        this.items = new ArrayList<>();
        totalCalculator = new TotalCalculator(this);
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return unmodifiableList(items);
    }

    public BigDecimal total() {
        return totalCalculator.calculate();
    }

}
