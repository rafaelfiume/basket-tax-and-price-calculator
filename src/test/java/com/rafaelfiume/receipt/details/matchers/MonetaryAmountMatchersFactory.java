package com.rafaelfiume.receipt.details.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import javax.money.MonetaryAmount;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;

public final class MonetaryAmountMatchersFactory {

    private MonetaryAmountMatchersFactory() {
        // not instantiable
    }

    public static Matcher<MonetaryAmount> is(String price) {
        return Matchers.is(moneyOf(price));
    }
}
