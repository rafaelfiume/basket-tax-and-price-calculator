package com.rafaelfiume.receipt.details;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryRounding;
import java.math.BigDecimal;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public enum ProductCategory implements TaxCalculator {

    BOOK {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price) {
            return moneyOf("0.00");
        }
    },

    MEDIA {
        private final BigDecimal REGULAR_TAX_RATE = new BigDecimal("0.1");

        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price) {
            MonetaryRounding rounding = Monetary.getDefaultRounding();
            return price.multiply(REGULAR_TAX_RATE).with(rounding);
        }
    },
    FOOD {
        @Override
        public MonetaryAmount calculateTax(MonetaryAmount price) {
            return moneyOf("0.00");
        }
    }

}
