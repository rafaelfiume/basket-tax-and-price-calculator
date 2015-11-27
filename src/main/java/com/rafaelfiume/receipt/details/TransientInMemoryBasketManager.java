package com.rafaelfiume.receipt.details;

import java.util.ArrayList;
import java.util.List;

import static com.rafaelfiume.receipt.details.ProductCategory.BOOK;
import static com.rafaelfiume.receipt.details.ProductCategory.FOOD;
import static com.rafaelfiume.receipt.details.ProductCategory.MEDIA;

/**
 * Not reusable basket manager: create a new one for each basket.
 *
 * (This could be a first version o BasketManager or a class to help writing tests)
 */
public class TransientInMemoryBasketManager implements BasketManager {

    private final Basket basket = new Basket();

    @Override
    public void add(Product p) {
        basket.add(p);
    }

    @Override
    public List<Product> selectedProducts() {
        return basket.products();
    }

    @Override
    public String totalPrice() {
        return null;
    }

    @Override
    public String totalTaxes() {
        return null;
    }
}
