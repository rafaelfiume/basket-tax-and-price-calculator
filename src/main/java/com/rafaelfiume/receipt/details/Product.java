package com.rafaelfiume.receipt.details;

public class Product {

    private final ProductCategory category;
    private final String name;
    private final String price;

    public Product(String name, ProductCategory category, String price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public String price() {
        return price;
    }
}
