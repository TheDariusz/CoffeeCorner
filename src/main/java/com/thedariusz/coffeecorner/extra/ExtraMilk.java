package com.thedariusz.coffeecorner.extra;


public record ExtraMilk() implements Extra {
    @Override
    public double getPrice() {
        return 0.3;
    }

    @Override
    public String getName() {
        return "Extra Milk";
    }
}
