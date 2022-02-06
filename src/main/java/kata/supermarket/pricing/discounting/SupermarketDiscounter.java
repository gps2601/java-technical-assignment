package kata.supermarket.pricing.discounting;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.groupingBy;

public class SupermarketDiscounter implements Discounter {
    private final Map<ProductName, Discount> discountedProducts = new HashMap<>();

    @Override
    public BigDecimal calculateDiscounts(List<Item> items) {
        Map<ProductName, List<Item>> itemsByDiscount = groupItemsByDiscountEligibility(items);
        return itemsByDiscount
                .entrySet()
                .stream()
                .map(productItems -> {
                    List<Item> discountableItems = productItems.getValue();
                    Discount discount = discountedProducts.get(productItems.getKey());
                    return discount.discountFor(discountableItems);
                })
                .reduce(BigDecimal::add)
                .orElse(ZERO);
    }

    public void addDiscount(ProductName name, Discount discount) {
        discountedProducts.put(name, discount);
    }

    private Map<ProductName, List<Item>> groupItemsByDiscountEligibility(List<Item> items) {
        Set<ProductName> productsWithDiscount = discountedProducts.keySet();
        return items
                .stream()
                .filter(item -> productsWithDiscount.contains(item.name()))
                .collect(groupingBy(Item::name));
    }
}
