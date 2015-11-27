package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;
import java.util.List;

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
    public BigDecimal totalPrice() {
        return basket.totalPrice();
    }

    @Override
    public BigDecimal totalTaxes() {
        return basket.totalTaxesToPay();
    }
}
