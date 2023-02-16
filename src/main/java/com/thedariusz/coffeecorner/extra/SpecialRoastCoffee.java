package com.thedariusz.coffeecorner.extra;


public record SpecialRoastCoffee() implements Extra {
    @Override
    public double getPrice() {
        return 0.9;
    }

    @Override
    public String getName() {
        return "Special Roast Coffee";
    }
}
