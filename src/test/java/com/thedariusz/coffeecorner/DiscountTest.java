package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DiscountTest extends BaseOrder {

    @Test    
    void shouldReturnFreeCoffeeDiscountForFifthBeverage() {
        Discount freeCoffeeDiscount = new Discount("Free " + fifthBeverageNoExtras.getName(), fifthBeverageNoExtras.getPrice());
        
        List<Discount> beverageDiscounts = Discount.getBeverageDiscounts(testOrder.getProducts());

        Assertions.assertThat(beverageDiscounts).contains(freeCoffeeDiscount);
    }

    @Test
    void shouldReturnFreeFirstExtraDiscountForExtrasAndSnack() {
        Coffee.Extra firstExtras = fourthBeverageWithFoamedMilkAndRoastCoffee.getExtras().get(0);
        Discount freeFirstExtraDiscount = new Discount("Free " + firstExtras.name(), firstExtras.price());

        List<Discount> extrasDiscount = Discount.getExtrasDiscounts(testOrder.getProducts());

        Assertions.assertThat(extrasDiscount).contains(freeFirstExtraDiscount);
    }

    @Test
    void shouldntReturnAnyDiscount() {
        Order orderWithoutDiscounts = new Order();
        orderWithoutDiscounts.addProduct(firstBeverageNoExtras);
        orderWithoutDiscounts.addProduct(secondBeverageNoExtras);
        orderWithoutDiscounts.addProduct(thirdBeverageNoExtras);

        List<Discount> extrasDiscount = Discount.getExtrasDiscounts(orderWithoutDiscounts.getProducts());
        List<Discount> beverageDiscounts = Discount.getBeverageDiscounts(orderWithoutDiscounts.getProducts());

        Assertions.assertThat(beverageDiscounts).isEmpty();
        Assertions.assertThat(extrasDiscount).isEmpty();
    }
}