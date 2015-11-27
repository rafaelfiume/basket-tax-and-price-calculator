package com.rafaelfiume.receipt.details;

import com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import javax.money.MonetaryAmount;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;
import static com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory.is;
import static com.rafaelfiume.receipt.details.support.ProductFactory.*;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void payNoTaxesSince_Books_AreExempt() {
        assertThat(a(book()).tax(), is("0.00"));
    }

    @Test
    public void payNoTaxesSince_Foods_AreExempt() {
        assertThat(a(chocolateBar()).tax(), is("0.00"));
    }

    @Test
    public void payRegularTaxesWhenBuyingMedia() {
        assertThat(a(musicCD()).tax(), is("1.50"));
    }

}
