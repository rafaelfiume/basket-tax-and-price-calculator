package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;

public interface TaxCalculator {

    MonetaryAmount calculateTax(MonetaryAmount price, ProductOrigin origin);
}
