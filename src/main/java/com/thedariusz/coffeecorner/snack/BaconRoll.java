package com.thedariusz.coffeecorner.snack;

public record BaconRoll() implements Snack {
    public double getPrice() {
        return 4.5;
    }

    public String getName() {
        return "Bacon Roll";
    }
}
