package com.rafaelfiume.receipt.details.support;

import com.rafaelfiume.receipt.details.Product;

import static com.rafaelfiume.receipt.details.ProductCategory.*;
import static com.rafaelfiume.receipt.details.ProductOrigin.IMPORTED;

public final class ProductFactory {

    private ProductFactory() {
        // not instantiable
    }

    public static Product a(Product p) {
        return p;
    }

    public static Product bookAt(String price) {
        return new Product("book", BOOK, price);
    }

    public static Product musicCdAt(String price) {
        return new Product("music CD", MEDIA, price);
    }

    public static Product chocolateBarAt(String price) {
        return new Product("chocolate bar", FOOD, price);
    }

    public static Product importedBoxOfChocolatesAt(String price) {
        return new Product("imported box of chocolate", FOOD, IMPORTED, price);
    }

    public static Product importedBottleOfPerfumeAt(String price) {
        return new Product("imported bottle of perfume", PERFUME, IMPORTED, price);
    }

}
