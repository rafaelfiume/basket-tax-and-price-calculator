package com.rafaelfiume.receipt.details.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.math.BigDecimal;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public final class MonetaryAmountMatchersFactory {

    private MonetaryAmountMatchersFactory() {
        // not instantiable
    }

    public static Matcher<BigDecimal> is(String price) {
        return Matchers.is(moneyOf(price));
    }
}
