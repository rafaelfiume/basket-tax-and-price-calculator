package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal calculateTaxes(BigDecimal price, ProductOrigin origin);
}
