package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;
import java.util.List;

public interface BasketManager {

    void add(Product p);

    List<Product> selectedProducts();

    BigDecimal totalPrice();

    BigDecimal totalTaxes();
}
