package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Juice;
import com.thedariusz.coffeecorner.products.Snack;
import org.junit.jupiter.api.BeforeEach;

public class BaseOrder {
    
    Order testOrder = new Order();

    final Coffee firstBeverageNoExtras = Coffee.small();
    final Coffee secondBeverageNoExtras = Coffee.medium();
    final Coffee thirdBeverageNoExtras = Coffee.large();
    final Coffee fourthBeverageWithFoamedMilkAndRoastCoffee = Coffee.large().withFoamedMilk().roastCoffee();
    final Snack snack = Snack.baconRoll();
    final Juice fifthBeverageNoExtras = Juice.orange();

    
    @BeforeEach
    void prepareBaseOrder() {
        testOrder.addProduct(firstBeverageNoExtras);
        testOrder.addProduct(secondBeverageNoExtras);
        testOrder.addProduct(thirdBeverageNoExtras);
        testOrder.addProduct(fourthBeverageWithFoamedMilkAndRoastCoffee);
        testOrder.addProduct(fifthBeverageNoExtras);
        testOrder.addProduct(snack);
    }
}
