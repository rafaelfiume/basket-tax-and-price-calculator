package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Instantiate one for each new shopping basket.
 */
public class Basket {

    private List<Product> selectedProducts = new ArrayList<>();

    private BigDecimal totalTaxToPay = MoneyDealer.moneyOf("0.00");
    private BigDecimal totalPriceWithoutTaxes = MoneyDealer.moneyOf("0.00");

    public Basket add(Product p) {
        selectedProducts.add(p);
        this.totalTaxToPay = totalTaxToPay.add(p.tax());
        this.totalPriceWithoutTaxes = totalPriceWithoutTaxes.add(p.price());

        return this;
    }

    public List<Product> products() {
        return new ArrayList<>(selectedProducts); // we don't want a client to inadvertently change the state of basket
    }

    public BigDecimal totalTaxesToPay() {
        return totalTaxToPay;
    }

    public BigDecimal totalPrice() {
        return totalPriceWithoutTaxes.add(totalTaxToPay);
    }
}
