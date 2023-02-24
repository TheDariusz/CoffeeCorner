package com.thedariusz.coffeecorner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class OrderTest extends BaseOrder {
    
    @Test
    void shouldHaveProductsFromBaseOrder() {
        Assertions.assertThat(testOrder.getProducts()).containsExactly(
                firstBeverageNoExtras, secondBeverageNoExtras, thirdBeverageNoExtras, 
                fourthBeverageWithExtras, fifthBeverageNoExtras, snack);
    }

    @Test
    void emptyOrderShouldCostZero() {
        Order emptyOrder = new Order();

        Assertions.assertThat(emptyOrder.getTotal()).isZero();
    }

    @Test
    void shouldReturnTotalPriceForBaseOrder() {
        Assertions.assertThat(testOrder.getTotal()).isEqualTo("21.90");
    }

    @Test
    void shouldReturnReceipt() {
        StringBuilder receipt = testOrder.getReceipt();

        Assertions.assertThat(receipt.toString()).hasToString(
                """
                        Small coffee - 2.50CHF
                        Medium coffee - 3.00CHF
                        Large coffee - 5.50CHF
                        Large coffee with foamed milk with special roast coffee - 6.90CHF
                        Freshly squeezed orange juice - 3.95CHF
                        Bacon Roll - 4.50CHF
                        ---------------
                        Total:   21.90CHF""");
    }
}