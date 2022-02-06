package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @Test
    void shouldDelegateToToTellerForWorkingOutPrice() {
        BigDecimal expectedPrice = new BigDecimal("5.99");
        Teller tellerDouble = items -> expectedPrice;
        Basket basket = new Basket(tellerDouble);

        assertEquals(expectedPrice, basket.total());
    }
}