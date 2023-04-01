package com.thedariusz.coffeecorner.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Coffee extends Product {

    private List<Extra> extras = new ArrayList<>();

    private Coffee(String name, BigDecimal price) {
        super(name, price);
    }

    private Coffee(String name, BigDecimal price, List<Extra> test) {
        super(name, price);
        this.extras= new ArrayList<>(test);
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public static Coffee small() {
        String name = "Small coffee";
        return new Coffee(name, Product.productCatalog().get(name));
    }

    public static Coffee medium() {
        String name = "Medium coffee";
        return new Coffee(name, Product.productCatalog().get(name));
    }

    public static Coffee large() {
        String name = "Large coffee";
        return new Coffee(name, Product.productCatalog().get(name));
    }

    public Coffee withFoamedMilk() {
        Extra foamedMilk = createExtras("foamed milk");
        extras.add(foamedMilk);
        return new Coffee(createNameWithExtras(foamedMilk.name), foamedMilk.price.add(getPrice()), extras);
    }

    public Coffee roastCoffee() {
        Extra roastCoffee = createExtras("special roast coffee");
        extras.add(roastCoffee);
        return new Coffee(createNameWithExtras(roastCoffee.name), roastCoffee.price.add(getPrice()), extras);
    }

    private String createNameWithExtras(String extrasName) {
        return getName() + " with " + extrasName;
    }
    
    private Extra createExtras(String extrasName) {
        return new Extra(extrasName, Product.productCatalog().get(extrasName));
    }

    public record Extra(String name,
                         BigDecimal price) {
    }
}
