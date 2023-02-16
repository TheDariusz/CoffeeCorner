package com.thedariusz.coffeecorner.extra;


public record FoamedMilk() implements Extra {
    @Override
    public double getPrice() {
        return 0.5;
    }

    @Override
    public String getName() {
        return "Foamed Milk";
    }
}
