package com.rafaelfiume.receipt.details;


import javax.money.MonetaryAmount;

import static com.rafaelfiume.receipt.details.ProductOrigin.REGULAR;

public class Product {

    private final String name;
    private final ProductCategory category;
    private final ProductOrigin origin;
    private final MonetaryAmount price;

    public Product(String name, ProductCategory category, String price) {
        this(name, category, REGULAR, price);
    }

    public Product(String name, ProductCategory category, ProductOrigin origin, String price) {
        this.name = name;
        this.category = category;
        this.origin = origin;
        this.price = MoneyDealer.moneyOf(price);
    }

    public String name() {
        return name;
    }

    public MonetaryAmount price() {
        return price;
    }

    public MonetaryAmount tax() {
        return category.calculateTax(this.price, this.origin);
    }

    public MonetaryAmount priceWithTaxes() {
        return price.add(tax());
    }
}
