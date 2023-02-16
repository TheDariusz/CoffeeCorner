package com.thedariusz.coffeecorner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {


    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added " + product.getName() + " to order, with price " + product.getPrice());
    }
    
    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal("0");
        
        for (Product product : products) {
            total=total.add(product.getPrice());
        }
        return total;
    }
    
    public String getReceipt() {
        String receipt="";
        for (Product product : products) {
            receipt+=product.getName() + " - " + product.getPrice() + "CHF\n";
        }
        receipt+="---------------\n";
        receipt+="Total:   " + getTotal() + "CHF";
        
        return receipt;
    }
}
