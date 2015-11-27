package com.rafaelfiume.receipt.details;

import java.util.ArrayList;
import java.util.List;

/**
 * Instantiate one for each new shopping basket.
 */
public class Basket {

    private List<Product> selectedProducts = new ArrayList<>();

    public Basket add(Product p) {
        selectedProducts.add(p);
        return this;
    }

    public List<Product> products() {
        return new ArrayList<>(selectedProducts); // we don't want a client to inadvertently change the state of basket
    }
}
