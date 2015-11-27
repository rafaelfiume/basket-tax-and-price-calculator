package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public enum ProductCategory implements TaxCalculator {

    BOOK,
    FOOD,
    MEDICINE,
    MEDIA {
        @Override
        public BigDecimal calculateTaxes(BigDecimal price, ProductOrigin origin) {
            return doCalculateTaxes(price, origin);
        }
    },
    PERFUME {
        @Override
        public BigDecimal calculateTaxes(BigDecimal price, ProductOrigin origin) {
            return doCalculateTaxes(price, origin);
        }
    };

    private static final BigDecimal GOODS_TAX_RATE = moneyOf("0.10");

    private static BigDecimal doCalculateTaxes(BigDecimal price, ProductOrigin origin) {
        final BigDecimal goodTaxes = roundToTheNearest(price.multiply(GOODS_TAX_RATE));
        final BigDecimal importationTaxes = roundToTheNearest(price.multiply(origin.taxRate()));

        return goodTaxes.add(importationTaxes);
        // After writing this code, I'm needing those packet of headache pills... :P
    }

    private static final BigDecimal roundToTheNearest(BigDecimal originalAmount) {
        final BigDecimal toTheNearest = new BigDecimal("0.05");

        final BigDecimal rounded = originalAmount
                .divide(toTheNearest, 9, BigDecimal.ROUND_CEILING)
                .setScale(0, RoundingMode.CEILING)
                .multiply(toTheNearest);

        return moneyOf(rounded);
    }

    @Override
    public BigDecimal calculateTaxes(BigDecimal price, ProductOrigin origin) {
        return roundToTheNearest(price.multiply(origin.taxRate()));
    }

}
