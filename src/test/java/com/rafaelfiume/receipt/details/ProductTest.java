package com.rafaelfiume.receipt.details;

import org.junit.Test;

import static com.rafaelfiume.receipt.details.ProductCategory.FOOD;
import static com.rafaelfiume.receipt.details.ProductCategory.PERFUME;
import static com.rafaelfiume.receipt.details.ProductOrigin.IMPORTED;
import static com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory.is;
import static com.rafaelfiume.receipt.details.support.ProductFactory.*;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void payNoTaxesSince_Books_AreExempt() {
        assertThat(a(book()).tax(), is("0.00"));
        assertThat(a(book()).priceWithTaxes(), is("12.49"));
    }

    @Test
    public void payNoTaxesSince_Foods_AreExempt() {
        assertThat(a(chocolateBar()).tax(), is("0.00"));
    }

    @Test
    public void payRegularTaxesWhenBuyingMedia() {
        assertThat(a(musicCD()).tax(), is("1.50"));
        assertThat(a(musicCD()).priceWithTaxes(), is("16.49"));
    }

    @Test
    public void payImportationTaxesEvenIfFoodsAreExempt() {
        assertThat(a(importedBoxOfChocolatesAt("10.00")).tax(), is("0.50"));
    }

    @Test
    public void payTaxesWhenImportingGoods() {
        assertThat(a(importedBottleOfPerfumeAt("47.50")).priceWithTaxes(), is("54.65"));
    }

    private Product importedBoxOfChocolatesAt(String price) {
        return new Product("imported box of chocolate", FOOD, IMPORTED, price);
    }

    private Product importedBottleOfPerfumeAt(String price) {
        return new Product("imported bottle of perfume", PERFUME, IMPORTED, price);
    }

}
