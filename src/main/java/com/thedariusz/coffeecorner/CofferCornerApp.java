package com.thedariusz.coffeecorner;


import com.thedariusz.coffeecorner.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class CofferCornerApp {

    private static final Logger logger = LoggerFactory.getLogger(CofferCornerApp.class);

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            logger.info("There is no products in your order");
            System.exit(0);
        }

        String[] arguments = args[0].split(",");
        Map<String, BigDecimal> productCatalog = Product.productCatalog();
        Order order = new Order();
        
        Arrays.stream(arguments)
                .map(String::trim)
                .filter(productCatalog::containsKey)
                .map(product -> new Product(product, productCatalog.get(product)))
                .forEach(order::addProduct);

        String receipt = Receipt.build(order).print();
        logger.info(receipt);

    }
}