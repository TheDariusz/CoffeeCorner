package com.thedariusz.coffeecorner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Receipt {
    private final Order order;

    private static final int MIN_LENGTH = 1;
    private final int maxReceiptWidth;
    private final String breakLine;
    private final String companyName;

    private final String titleFormat;

    private final String receiptTimeStamp;


    private Receipt(Order order) {
        LocalDateTime now = LocalDateTime.now();
        this.order = order;
        this.receiptTimeStamp = "Receipt from " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.maxReceiptWidth = getReceiptWidth();
        this.breakLine = "|" + "-".repeat(maxReceiptWidth + 1) + "|";
        this.companyName = "Coffee Corner Lmt.";
        this.titleFormat = String.format("|%%-%ds|", maxReceiptWidth + 1);
    }

    private int getReceiptWidth() {
        return max(getLongestProductName() + getLongestPriceString() + ":".length(), receiptTimeStamp.length());
    }

    public static Receipt build(Order order) {
        return new Receipt(order);
    }

    public String getReceiptTimeStamp() {
        return receiptTimeStamp;
    }

    public String print() {
        return header() + orderedProductsFormatted() + discounts() + summary();
    }

    private StringBuilder header() {
        StringBuilder header = new StringBuilder();
        return header.append("\n").append(breakLine).append("\n")
                .append(String.format(titleFormat, companyName)).append("\n")
                .append(String.format(titleFormat, receiptTimeStamp)).append("\n")
                .append(breakLine).append("\n");
    }

    private String orderedProductsFormatted() {
        StringBuilder lines = new StringBuilder();
        String noProductInfo = String.format(titleFormat, "no products ordered");

        String begin = String.format(titleFormat, "Products ordered:");
        String productsLines = order.getProducts().stream()
                .map(product -> formatProductPriceLine(product.getName(), product.getPrice()))
                .collect(Collectors.joining("\n"));
        lines.append(begin).append("\n");
        lines.append(productsLines.length() > 0 ? productsLines : noProductInfo);
        lines.append("\n").append(breakLine).append("\n");
        return lines.toString();
    }

    private String summary() {
        return formatProductPriceLine("Summary", order.getTotal()) + "\n" +
                breakLine;
    }

    private String discounts() {
        StringBuilder lines = new StringBuilder();

        String begin = String.format(titleFormat, "Discounts:");
        String discountLines = order.getDiscounts().stream()
                .map(discount -> formatProductPriceLine(discount.description(), discount.discount().negate()))
                .collect(Collectors.joining("\n"));
        String noDiscountInfo = String.format(titleFormat, "no discounts available");

        return lines.append(begin).append("\n")
                .append(discountLines.length() > 0 ? discountLines : noDiscountInfo)
                .append("\n").append(breakLine).append("\n")
                .toString();
    }

    private String formatProductPriceLine(String name, BigDecimal price) {
        String format = String.format("|%%-%ds %%%ds|", maxReceiptWidth - getLongestPriceString(), getLongestPriceString());
        return String.format(format, name + ":", price.toString());
    }

    private int getLongestProductName() {
        Integer maxProductNameLength = order.getProducts().stream()
                .map(product -> product.getName().length())
                .max(Integer::compareTo)
                .orElse(MIN_LENGTH);

        Integer maxDiscountDescLength = order.getDiscounts().stream()
                .map(discount -> discount.description().length())
                .max(Integer::compareTo)
                .orElse(MIN_LENGTH);

        return max(maxProductNameLength, maxDiscountDescLength);
    }

    private int getLongestPriceString() {
        Integer maxProductPriceLength = order.getProducts().stream()
                .map(product -> getPriceLength(product.getPrice()))
                .max(Integer::compareTo)
                .orElse(MIN_LENGTH);

        Integer maxDiscountPriceLength = order.getDiscounts().stream()
                .map(discount -> getPriceLength(discount.discount().negate()))
                .max(Integer::compareTo)
                .orElse(MIN_LENGTH);

        return max(max(maxProductPriceLength, maxDiscountPriceLength), getPriceLength(order.getTotal()));
    }

    private int getPriceLength(BigDecimal price) {
        return price.toString().length();
    }
}
