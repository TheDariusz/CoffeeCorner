package com.thedariusz.coffeecorner;

public record Receipt(
        Order order
) {

    @Override
    public String toString() {
        return "Receipt{" +
                "order=" + order +
                '}';
    }
}
