package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Juice;
import com.thedariusz.coffeecorner.products.Snack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class OrderTest {
    final Coffee smallCoffee = Coffee.small();
    final Coffee mediumCoffee = Coffee.medium();
    final Coffee largeCoffee = Coffee.large();
    final Coffee largeRoastCoffeeWithFoamedMilk = Coffee.large().withFoamedMilk().roastCoffee();
    final Snack baconRoll = Snack.baconRoll();
    final Juice orangeJuice = Juice.orange();

    @Test
    void shouldAddProductToOrder() {

        Order testOrder = new Order();

        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);
        testOrder.addProduct(largeCoffee);
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        

        Assertions.assertThat(testOrder.getProducts()).containsExactly(smallCoffee, mediumCoffee, largeCoffee,
                baconRoll, orangeJuice);
    }

    @Test
    void emptyOrderShouldCostZero() {
        Order testOrder = new Order();

        Assertions.assertThat(testOrder.getTotal()).isZero();
    }

    @Test
    void shouldReturnTotalPriceFor5CoffeeWithDiscountAndSnack() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);
        testOrder.addProduct(largeCoffee);

        Assertions.assertThat(testOrder.getTotal()).isEqualTo("20.85");
    }

    @Test
    void shouldReturnReceipt() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        

        StringBuilder receipt = testOrder.getReceipt();

        Assertions.assertThat(receipt.toString()).hasToString(
                """
                        Large coffee with foamed milk with special roast coffee - 6.90CHF
                        Bacon Roll - 4.50CHF
                        Freshly squeezed orange juice - 3.95CHF
                        ---------------
                        Total:   15.35CHF""");
    }
    
    @Test
    void shouldReturnBeverageDiscountCountingEveryFifthBeverage() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk); 
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);
        testOrder.addProduct(largeCoffee); //5.5
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);
        testOrder.addProduct(largeCoffee); 
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk); //6.9
        
        Assertions.assertThat(testOrder.getTotalBeverageDiscount()).isEqualTo("12.40");
    }

    @Test
    void shouldntReturnBeverageDiscountCountingFourBeveragesAndSnack() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);
        testOrder.addProduct(baconRoll);
        testOrder.addProduct(orangeJuice);
        testOrder.addProduct(smallCoffee);
        testOrder.addProduct(mediumCoffee);

        Assertions.assertThat(testOrder.getTotalBeverageDiscount()).isZero();
    }
    
    @Test
    void shouldReturnExtraDiscountForFoamedMilkWithSnack() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);
        testOrder.addProduct(baconRoll);
        
        Assertions.assertThat(testOrder.getExtrasDiscount()).isEqualTo("0.50");
    }

    @Test
    void shouldntReturnExtraDiscountForFoamedMilkWithoutSnack() {
        Order testOrder = new Order();
        testOrder.addProduct(largeRoastCoffeeWithFoamedMilk);
        testOrder.addProduct(orangeJuice);

        Assertions.assertThat(testOrder.getExtrasDiscount()).isZero();
    }
}