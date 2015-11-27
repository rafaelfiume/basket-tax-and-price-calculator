package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;
import java.util.List;

public interface BasketManager {

    void add(Product p);

    List<Product> selectedProducts();

    MonetaryAmount totalPrice();

    MonetaryAmount totalTaxes();
}
