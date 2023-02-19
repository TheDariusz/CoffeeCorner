package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Coffee;
import com.thedariusz.coffeecorner.products.Juice;
import com.thedariusz.coffeecorner.products.Product;
import com.thedariusz.coffeecorner.products.Snack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Order {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        logger.info("Added {} to order, with price {}", product.getName(), product.getPrice());
    }

    public BigDecimal getTotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(getTotalBeverageDiscount());
    }

    public StringBuilder getReceipt() {
        StringBuilder receipt = new StringBuilder();
        for (Product product : products) {
            receipt.append(product.getName()).append(" - ");
            receipt.append(product.getPrice()).append("CHF\n");
        }
        receipt.append("---------------\n");
        receipt.append("Total:   ").append(getTotal()).append("CHF");

        return receipt;
    }

    public BigDecimal getTotalBeverageDiscount() {
        List<Product> beverages = products.stream()
                .filter(this::isBeverage)
                .toList();

        return IntStream.rangeClosed(1, beverages.size())
                .filter(this::isFifthNumber)
                .mapToObj(i -> beverages.get(i - 1))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isFifthNumber(int i) {
        return i % 5 == 0;
    }

    public BigDecimal getExtrasDiscount() {
        boolean hasBeverageAndSnack = products.stream()
                .anyMatch(this::isBeverage) &&
                products.stream().anyMatch(Snack.class::isInstance);

        if (hasBeverageAndSnack) {
            return products.stream()
                    .filter(Coffee.class::isInstance)
                    .map(product -> ((Coffee) product).getExtras())
                    .flatMap(Collection::stream)
                    .findFirst()
                    .map(Coffee.Extra::price)
                    .orElse(BigDecimal.ZERO);
        }
        return BigDecimal.ZERO;
    }

    private boolean isBeverage(Product product) {
        return product instanceof Coffee || product instanceof Juice;
    }
}
