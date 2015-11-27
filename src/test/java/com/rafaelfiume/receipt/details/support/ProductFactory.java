package com.rafaelfiume.receipt.details.support;

import com.rafaelfiume.receipt.details.Product;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

import static com.rafaelfiume.receipt.details.ProductCategory.BOOK;
import static com.rafaelfiume.receipt.details.ProductCategory.FOOD;
import static com.rafaelfiume.receipt.details.ProductCategory.MEDIA;

public class ProductFactory {

    public static Product a(Product p) {
        return p;
    }

    public static Product book() {
        return new Product("book", BOOK, "12.49");
    }

    public static Product musicCD() {
        return new Product("music CD", MEDIA, "14.99");
    }

    public static Product chocolateBar() {
        return new Product("chocolate bar", FOOD, "0.85");
    }
    
}
