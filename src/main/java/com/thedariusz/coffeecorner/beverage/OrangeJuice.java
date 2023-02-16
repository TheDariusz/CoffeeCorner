package com.thedariusz.coffeecorner.beverage;


public record OrangeJuice(
        double quantity) implements Beverage {

    @Override
    public double getPrice() {
        return 3.95 * quantity;
    }

    @Override
    public String getName() {
        return quantity + " Freshly Squeezed Orange Juice";
    }
}
