package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

/**
 * Instantiate one for each new shopping basket.
 */
public class Basket {

    private List<Product> selectedProducts = new ArrayList<>();

    private MonetaryAmount totalTaxToPay = MoneyDealer.moneyOf("0.00");
    private MonetaryAmount totalPriceWithoutTaxes = MoneyDealer.moneyOf("0.00");

    public Basket add(Product p) {
        selectedProducts.add(p);
        this.totalTaxToPay = totalTaxToPay.add(p.tax());
        this.totalPriceWithoutTaxes = totalPriceWithoutTaxes.add(p.price());

        return this;
    }

    public List<Product> products() {
        return new ArrayList<>(selectedProducts); // we don't want a client to inadvertently change the state of basket
    }

    public MonetaryAmount totalTaxesToPay() {
        return totalTaxToPay;
    }

    public MonetaryAmount totalPrice() {
        return totalPriceWithoutTaxes.add(totalTaxToPay);
    }
}
