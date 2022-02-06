package kata.supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Item> items;
    private final Teller teller;

    public Basket() {
        this.items = new ArrayList<>();
        teller = new Teller();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    public BigDecimal total() {
        return teller.calculate(items);
    }
}
