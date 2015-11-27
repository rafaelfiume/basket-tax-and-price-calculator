package com.rafaelfiume.receipt.details;

import java.math.BigDecimal;

public class MoneyDealer {

    public static BigDecimal moneyOf(String money) {
        return new BigDecimal(money).setScale(2);
    }

    public static BigDecimal moneyOf(BigDecimal money) {
        return money.setScale(2);
    }

}
