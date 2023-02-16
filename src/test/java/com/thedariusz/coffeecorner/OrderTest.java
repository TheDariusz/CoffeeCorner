package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Snack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class OrderTest {
    final Coffee smallCoffee = Coffee.small();
    final Coffee mediumCoffee = Coffee.medium();
    final Coffee largeCoffee = Coffee.large();
    final Coffee smallCoffeeWithFoamedMilk = Coffee.small().withFoamedMilk();
    final Coffee largeRoastCoffeeWithFoamedMilk = Coffee.large().withFoamedMilk().roastCoffee();
    final Snack snack = new Snack("Bacon roll", new BigDecimal("5.1"));

    @Test
    void shouldAddProductToOrder() {

        Order testOrder = new Order();

        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);
        testOrder.addProduct(largeCoffee);

        Assertions.assertThat(testOrder.getProducts()).containsExactly(smallCoffee, mediumCoffee, largeCoffee);
    }

    @Test
    void emptyOrderShouldCostZero() {
        Order testOrder = new Order();

        Assertions.assertThat(testOrder.getTotal()).isZero();
    }

    @Test
    void shouldReturnTotalPriceForLargeCoffeeAndSmallCoffeeWithFoamedMilk() {
        Order testOrder = new Order();
        testOrder.addProduct(largeCoffee);
        testOrder.addProduct(smallCoffeeWithFoamedMilk);

        Assertions.assertThat(testOrder.getTotal()).isEqualTo("8.50");
    }

    @Test
    void shouldReturnTotalPriceForLargeRoastCoffeeAndWithFoamedMilk() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);

        Assertions.assertThat(testOrder.getTotal()).isEqualTo("6.90");
    }

    @Test
    void shoudReturnReceipt() {
        Order testOrder = new Order();
        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(smallCoffeeWithFoamedMilk);

        String receipt = testOrder.getReceipt();

        Assertions.assertThat(receipt).isEqualTo(
                """
                        Small coffee - 2.50CHF
                        Small coffee with foamed milk - 3.00CHF
                        ---------------
                        Total:   5.50CHF""");
    }
}