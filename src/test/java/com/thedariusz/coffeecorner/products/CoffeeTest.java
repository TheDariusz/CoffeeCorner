package com.thedariusz.coffeecorner.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class CoffeeTest {

    final Coffee largeRoastCoffeeWithFoamedMilk = Coffee.large().withFoamedMilk().roastCoffee();
    
    @Test
    void shouldReturnListOfExtras() {
        List<Coffee.Extra> extras = largeRoastCoffeeWithFoamedMilk.getExtras();
        Coffee.Extra foamedMilk = new Coffee.Extra("foamed milk", new BigDecimal("0.50"));
        Coffee.Extra roastCoffee = new Coffee.Extra("special roast coffee", new BigDecimal("0.90"));
        

        Assertions.assertThat(extras).contains(foamedMilk, roastCoffee);
    }

}