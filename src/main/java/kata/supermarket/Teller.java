package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface Teller {
    BigDecimal calculate(List<Item> items);
}
