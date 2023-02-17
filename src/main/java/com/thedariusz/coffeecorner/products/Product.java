package com.thedariusz.coffeecorner.products;

import java.math.BigDecimal;
import java.util.Map;

public class Product {
    String name;
    BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    static Map<String, BigDecimal> productCatalog() {
        return Map.of(
                "Small coffee", new BigDecimal("2.50"),
                "Medium coffee", new BigDecimal("3.00"),
                "Large coffee", new BigDecimal("5.50"),
                "extra milk", new BigDecimal("0.30"),
                "foamed milk", new BigDecimal("0.50"),
                "special roast coffee", new BigDecimal("0.90"),
                "Bacon Roll", new BigDecimal("4.50"),
                "Freshly squeezed orange juice", new BigDecimal("3.95")
        );
    }
}
