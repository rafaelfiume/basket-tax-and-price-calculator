package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public enum ProductOrigin {

    NATIONAL("0.00"),
    IMPORTED("0.05");

    private final BigDecimal taxRate;

    ProductOrigin(String taxRate) {
        this.taxRate = moneyOf(taxRate);
    }

    public BigDecimal taxRate() {
        return taxRate;
    }
}
