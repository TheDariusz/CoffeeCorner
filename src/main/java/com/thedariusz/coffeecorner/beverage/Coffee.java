package com.thedariusz.coffeecorner.beverage;

public record Coffee(
        String size,
        double price
) implements Beverage {
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return size + "coffee";
    }
}
