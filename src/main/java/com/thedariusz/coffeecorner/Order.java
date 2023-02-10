package com.thedariusz.coffeecorner;

import com.thedariusz.coffeecorner.beverage.Beverage;
import com.thedariusz.coffeecorner.extra.Extra;
import com.thedariusz.coffeecorner.snack.Snack;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Beverage> beverages = new ArrayList<>();
    private List<Snack> snacks = new ArrayList<>();
    private List<Extra> extras = new ArrayList<>();
    private int noOrders = 0;

    public Order() {
        noOrders++;
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
        if (noOrders % 5 == 0) {
            System.out.println("Congratulations! Your " + beverage.getName() + " is for free!");
        } else {
            System.out.println("Added " + beverage.getName() + " to your order. Price: " + beverage.getPrice() + " CHF");
        }
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
        System.out.println("Added " + snack.getName() + " to your order. Price: " + snack.getPrice() + " CHF");
    }

    public void addExtra(Extra extra) {
        extras.add(extra);
        System.out.println("Added " + extra.getName() + " to your order. Price: " + extra.getPrice() + " CHF");
    }

    public double getTotal() {
        double total = 0.0;
        for (Beverage beverage : beverages) {
            total += beverage.getPrice();
        }
        if (noOrders % 5 == 0) {
            total -= beverages.get(0).getPrice();
            System.out.println("Congratulations! Your " + beverages.get(0).getName() + " is for free!");
        }

        for (Snack snack : snacks) {
            total += snack.getPrice();
        }
        for (Extra extra : extras) {
            total += extra.getPrice();
        }

        if (!snacks.isEmpty() && !beverages.isEmpty()) {
            total -= extras.get(0).getPrice();
            System.out.println("Congratulations! One of your extras is for free!");
        }
        return total;
    }
}
