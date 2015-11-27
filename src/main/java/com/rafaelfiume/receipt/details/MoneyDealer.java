package com.rafaelfiume.receipt.details;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public class MoneyDealer {

    public static MonetaryAmount moneyOf(String money) {
        return Money.of(new BigDecimal(money), "EUR");
    }

    public static MonetaryAmount moneyOf(BigDecimal money) {
        return Money.of(money, "EUR");
    }

}
