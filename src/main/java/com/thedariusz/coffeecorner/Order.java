package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }


    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public List<Discount> getDiscounts() {
        return Discount.calculate(products);
    }

    public void addProduct(Product product) {
        products.add(product);
        logger.info("Added {} to order, with price {}", product.getName(), product.getPrice());
    }

    public BigDecimal getTotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(getDiscountSum());
    }

    private BigDecimal getDiscountSum() {
        return getDiscounts().stream()
                .map(Discount::discount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
