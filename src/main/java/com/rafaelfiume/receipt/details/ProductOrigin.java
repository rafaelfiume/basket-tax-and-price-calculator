package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public enum ProductOrigin {

    REGULAR("0.00"),
    IMPORTED("0.05");

    private final MonetaryAmount taxRate;

    ProductOrigin(String taxRate) {
        this.taxRate = moneyOf(taxRate);
    }

    public MonetaryAmount taxRate() {
        return taxRate;
    }
}
