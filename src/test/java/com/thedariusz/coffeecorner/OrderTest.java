package com.thedariusz.coffeecorner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class OrderTest extends BaseOrder {

    @Test
    void shouldHaveProductsFromBaseOrder() {
        Assertions.assertThat(testOrder.getProducts()).containsExactly(
                firstBeverageNoExtras, secondBeverageNoExtras, thirdBeverageNoExtras,
                fourthBeverageWithFoamedMilkAndRoastCoffee, fifthBeverageNoExtras, snack);
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
}