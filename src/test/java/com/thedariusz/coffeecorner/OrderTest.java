package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Juice;
import com.thedariusz.coffeecorner.products.Snack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrderTest {
    final Coffee firstBeverageNoExtras = Coffee.small();
    final Coffee secondBeverageNoExtras = Coffee.medium();
    final Coffee thirdBeverageNoExtras = Coffee.large();
    final Coffee fourthBeverageWithExtras = Coffee.large().withFoamedMilk().roastCoffee();
    final Snack snack = Snack.baconRoll();
    final Juice fifthBeverageNoExtras = Juice.orange();

    Order testOrder = new Order();

    @BeforeEach
    void prepareBaseOrder() {
        testOrder.addProduct(firstBeverageNoExtras);
        testOrder.addProduct(secondBeverageNoExtras);
        testOrder.addProduct(thirdBeverageNoExtras);
        testOrder.addProduct(fourthBeverageWithExtras);
        testOrder.addProduct(fifthBeverageNoExtras);
        testOrder.addProduct(snack);
    }
    
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
        Assertions.assertThat(testOrder.getTotal()).isEqualTo("20.85");
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
                        Total:   22.40CHF""");
    }
    
    @Test
    void shouldReturnBeverageDiscountForEveryFifthBeverage() {
        Assertions.assertThat(testOrder.getEveryFifthBeverage()).isEqualTo("3.95");
    }

    @Test
    void shouldntReturnBeverageDiscountForFourBeveragesAndSnack() {
        Order orderWithOneBeverage = new Order();
        orderWithOneBeverage.addProduct(firstBeverageNoExtras);

        Assertions.assertThat(orderWithOneBeverage.getEveryFifthBeverage()).isZero();
    }
    
    @Test
    void shouldReturnDiscountForBeverageWithExtrasAndSnack() {
        Assertions.assertThat(testOrder.getFreeExtras()).isEqualTo("0.50");
    }

    @Test
    void shouldntReturnExtraDiscountForFoamedMilkWithoutSnack() {
        Order orderWithoutSnack = new Order();
        orderWithoutSnack.addProduct(firstBeverageNoExtras);
        orderWithoutSnack.addProduct(secondBeverageNoExtras);

        Assertions.assertThat(orderWithoutSnack.getFreeExtras()).isZero();
    }
}