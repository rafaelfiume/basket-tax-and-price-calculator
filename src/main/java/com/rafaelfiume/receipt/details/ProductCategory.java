package com.rafaelfiume.receipt.details;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public enum ProductCategory implements TaxCalculator {

    BOOK {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price, ProductOrigin origin) {
            return price.multiply(origin.taxRate().getNumber());
        }
    },

    MEDIA {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price, ProductOrigin origin) {
            return calculateTaxes(price, origin);
        }
    },

    FOOD {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price, ProductOrigin origin) {
            return price.multiply(origin.taxRate().getNumber());
        }
    },

    PERFUME {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price, ProductOrigin origin) {
            return calculateTaxes(price, origin);
        }
    };

    private static final MonetaryAmount GOODS_TAX_RATE = moneyOf("0.10");

    private static MonetaryAmount calculateTaxes(MonetaryAmount price, ProductOrigin origin) {
        final MonetaryAmount goodTaxes = roundToTheNearest(price.multiply(GOODS_TAX_RATE.getNumber()));
        final MonetaryAmount importationTaxes = roundToTheNearest(price.multiply(origin.taxRate().getNumber()));

        return goodTaxes.add(importationTaxes);
    }

    private static final MonetaryAmount roundToTheNearest(MonetaryAmount amount) {
        final BigDecimal originalPrice = amount.getNumber().numberValue(BigDecimal.class);
        final BigDecimal toTheNearest = new BigDecimal("0.05");

        final BigDecimal rounded = originalPrice
                .divide(toTheNearest, HALF_EVEN)
                .setScale(0, HALF_UP)
                .multiply(toTheNearest);

        return moneyOf(rounded);
    }

}
