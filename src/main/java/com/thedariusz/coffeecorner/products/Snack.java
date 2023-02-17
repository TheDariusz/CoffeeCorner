package com.thedariusz.coffeecorner.products;

import java.math.BigDecimal;

public class Snack extends Product {

    private Snack(String name, BigDecimal price) {
        super(name, price);
    }
    
    public static Snack baconRoll() {
        String name = "Bacon Roll";
        return new Snack(name, productCatalog().get(name));
    }
}
