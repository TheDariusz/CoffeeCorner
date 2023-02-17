package com.thedariusz.coffeecorner.products;


import java.math.BigDecimal;

public class Juice extends Product {

    private final String type;

    private Juice(String name, BigDecimal price, String type) {
        super(name, price);
        this.type=type;
    }
    
    public static Juice orange() {
        String name = "Freshly squeezed orange juice";
        return new Juice(name, productCatalog().get(name), "Orange");
    }

    public String getType() {
        return type;
    }
}
