package com.rafaelfiume.receipt.details;

import java.util.List;

public interface BasketManager {

    void add(Product p);

    List<Product> selectedProducts();

    String totalPrice();

    String totalTaxes();
}
