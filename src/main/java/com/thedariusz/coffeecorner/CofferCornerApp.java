package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.beverage.Coffee;
import com.thedariusz.coffeecorner.beverage.OrangeJuice;
import com.thedariusz.coffeecorner.extra.ExtraMilk;
import com.thedariusz.coffeecorner.extra.FoamedMilk;
import com.thedariusz.coffeecorner.snack.BaconRoll;

public class CofferCornerApp {
    public static void main(String[] args) {
        Order order = new Order();
        order.addBeverage(new Coffee("Small", 2.5));
        order.addBeverage(new Coffee("Medium", 3.0));
        order.addBeverage(new OrangeJuice(0.25));
        order.addSnack(new BaconRoll());
        order.addExtra(new ExtraMilk());
        order.addExtra(new FoamedMilk());
        System.out.println("Total: " + order.getTotal() + " CHF");
    }
}