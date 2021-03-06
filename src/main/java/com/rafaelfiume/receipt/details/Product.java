package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;

import static com.rafaelfiume.receipt.details.ProductOrigin.NATIONAL;

public class Product {

    private final String name;
    private final ProductCategory category;
    private final ProductOrigin origin;
    private final BigDecimal price;

    public Product(String name, ProductCategory category, String price) {
        this(name, category, NATIONAL, price);
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

    public BigDecimal price() {
        return price;
    }

    public BigDecimal tax() {
        return category.calculateTaxes(this.price, this.origin);
    }

    public BigDecimal priceWithTaxes() {
        return price.add(tax());
    }
}
