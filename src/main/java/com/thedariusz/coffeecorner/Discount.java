package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Juice;
import com.thedariusz.coffeecorner.products.Product;
import com.thedariusz.coffeecorner.products.Snack;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Discount {
    
    private final List<Product> products;

    public Discount(List<Product> products) {
        this.products = products;
    }

    public BigDecimal calculate() {
        BigDecimal freeExtraDiscount = getExtraForFree().
                map(Coffee.Extra::price).
                orElse(BigDecimal.ZERO);

        BigDecimal freeFifthCoffeeDiscount = getEveryFifthBeverage().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return freeExtraDiscount.add(freeFifthCoffeeDiscount);
    }

    public Optional<Coffee.Extra> getExtraForFree() {
        List<Coffee.Extra> coffeeExtras = getCoffeesExtrasList();
        if (hasBeverageAndSnack() && !coffeeExtras.isEmpty()) {
            return coffeeExtras.stream()
                    .findFirst();
        }
        return Optional.empty();
    }

    public List<Product> getEveryFifthBeverage() {
        List<Product> beverages = products.stream()
                .filter(this::isBeverage)
                .toList();

        int beveragesListSize = beverages.size();

        if (beveragesListSize <5) {
            return Collections.emptyList();
        }

        return IntStream.rangeClosed(1, beveragesListSize)
                .filter(i -> i%5==0)
                .mapToObj(i -> beverages.get(i - 1))
                .toList();
    }
    
    private List<Coffee.Extra> getCoffeesExtrasList() {
        return products.stream()
                .filter(Coffee.class::isInstance)
                .map(product -> ((Coffee) product).getExtras())
                .flatMap(Collection::stream)
                .toList();
    }


    private boolean hasBeverageAndSnack() {
        return products.stream()
                .anyMatch(this::isBeverage) &&
                products.stream().anyMatch(Snack.class::isInstance);
    }

    private boolean isBeverage(Product product) {
        return product instanceof Coffee || product instanceof Juice;
    }
}
