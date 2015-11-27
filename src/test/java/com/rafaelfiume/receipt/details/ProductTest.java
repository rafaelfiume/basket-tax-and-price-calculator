package com.rafaelfiume.receipt.details;

import org.junit.Test;

import static com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory.is;
import static com.rafaelfiume.receipt.details.support.ProductFactory.*;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void shouldPayNoTaxesSince_Books_AreExempt() {
        assertThat(a(bookAt("12.49")).tax(), is("0.00"));
        assertThat(a(bookAt("12.49")).priceWithTaxes(), is("12.49"));
    }

    @Test
    public void shouldPayNoTaxesSince_Foods_AreExempt() {
        assertThat(a(chocolateBarAt("0.85")).tax(), is("0.00"));
    }

    @Test
    public void shouldPayRegularTaxesWhenBuyingMedia() {
        assertThat(a(musicCdAt("14.99")).tax(), is("1.50"));
        assertThat(a(musicCdAt("14.99")).priceWithTaxes(), is("16.49"));
    }

    @Test
    public void shouldPayImportationTaxesEvenIfFoodsAreExempt() {
        assertThat(a(importedBoxOfChocolatesAt("10.00")).tax(), is("0.50"));
        assertThat(a(importedBoxOfChocolatesAt("10.00")).priceWithTaxes(), is("10.50"));
    }

    @Test
    public void shouldPayTaxesWhenImportingGoods() {
        assertThat(a(importedBottleOfPerfumeAt("47.50")).tax(), is("7.15"));
        assertThat(a(importedBottleOfPerfumeAt("47.50")).priceWithTaxes(), is("54.65"));
    }

    @Test
    public void shouldRound_Up_TaxesToTheNearest00Dot5() {
        assertThat(a(importedBoxOfChocolatesAt("11.25")).tax(), is("0.60")); // tax without any rounding is 0.5625 (is someone trying to be a millionaire making 0.03p more in a sale, or they just don't developers much?)
    }
}
