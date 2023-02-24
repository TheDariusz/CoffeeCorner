package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Product;
import com.thedariusz.coffeecorner.products.Snack;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Discount(

        String description, BigDecimal discount) {

    public static List<Discount> calculate(List<Product> products) {
        List<Discount> beverageDiscounts = getBeverageDiscounts(products);
        List<Discount> extrasDiscounts = getExtrasDiscounts(products);
        return Stream.of(beverageDiscounts, extrasDiscounts)
                .flatMap(Collection::stream)
                .toList();
    }

    public static List<Discount> getBeverageDiscounts(List<Product> products) {
        return getEveryFifthBeverage(products).stream()
                .map(product -> new Discount("Free " + product.getName(), product.getPrice()))
                .toList();
    }

    public static List<Discount> getExtrasDiscounts(List<Product> products) {
        return getExtraForFree(products)
                .map(extra -> List.of(new Discount("Free " + extra.name(), extra.price())))
                .orElse(Collections.emptyList());
    }

    private static List<Product> getEveryFifthBeverage(List<Product> products) {
        List<Product> beverages = products.stream().filter(Product::isBeverage).toList();

        int beveragesListSize = beverages.size();

        if (beveragesListSize < 5) {
            return Collections.emptyList();
        }

        return IntStream.rangeClosed(1, beveragesListSize).filter(i -> i % 5 == 0).mapToObj(i -> beverages.get(i - 1)).toList();
    }

    private static Optional<Coffee.Extra> getExtraForFree(List<Product> products) {
        List<Coffee.Extra> coffeeExtras = getCoffeesExtrasList(products);
        if (hasBeverageAndSnack(products) && !coffeeExtras.isEmpty()) {
            return coffeeExtras.stream().findFirst();
        }
        return Optional.empty();
    }

    private static List<Coffee.Extra> getCoffeesExtrasList(List<Product> products) {
        return products.stream().filter(Coffee.class::isInstance).map(product -> ((Coffee) product).getExtras()).flatMap(Collection::stream).toList();
    }

    private static boolean hasBeverageAndSnack(List<Product> products) {
        return products.stream().anyMatch(Product::isBeverage) && products.stream().anyMatch(Snack.class::isInstance);
    }

}
