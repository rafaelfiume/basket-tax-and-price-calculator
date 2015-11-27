package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;

public class Product {

    private final ProductCategory category;
    private final String name;
    private final MonetaryAmount price;

    public Product(String name, ProductCategory category, String price) {
        this.name = name;
        this.category = category;
        this.price = MoneyDealer.moneyOf(price);
    }

    public String name() {
        return name;
    }

    public MonetaryAmount price() {
        return price;
    }

    public MonetaryAmount tax() {
        return category.calculateTax(this.price);
    }

}
