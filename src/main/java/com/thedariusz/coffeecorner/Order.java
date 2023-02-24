package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final List<Product> products = new ArrayList<>();


    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public void addProduct(Product product) {
        products.add(product);
        logger.info("Added {} to order, with price {}", product.getName(), product.getPrice());
    }

    public BigDecimal getTotal() {
        List<Discount> discounts = Discount.calculate(products);

        BigDecimal discountSum = discounts.stream()
                .map(Discount::discount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(discountSum);
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


}
