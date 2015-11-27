package com.rafaelfiume.receipt.details;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.rafaelfiume.receipt.details.MoneyDealer.moneyOf;
import static com.rafaelfiume.receipt.details.matchers.ProductMatcher.one;
import static com.rafaelfiume.receipt.details.support.ProductFactory.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() {
        basket = new Basket();
    }

    @Test
    public void shouldBeAbleToRetrieveAddedItemsToTheBasket() {
        // given
        basket.add(a(bookAt("12.49"))).add(a(musicCdAt("14.99"))).add(a(chocolateBarAt("0.85")));

        // when
        final List<Product> selectedProducts = basket.products();

        // then
        assertThat(selectedProducts.size(), is(3));
        assertThat(selectedProducts, contains(one(bookAt("12.49")), one(musicCdAt("14.99")), one(chocolateBarAt("0.85"))));
    }

    @Test
    public void shouldCalculateTotalTaxesForProductsInTheBasket() {
        basket.add(a(bookAt("12.49"))).add(a(musicCdAt("14.99"))).add(a(chocolateBarAt("0.85")));

        assertThat(basket.totalTaxesToPay(), isTheAmountOf("1.50"));
    }

    @Test
    public void shouldCalculateTotalPriceForProductsInTheBasket() {
        basket.add(a(bookAt("12.49"))).add(a(musicCdAt("14.99"))).add(a(chocolateBarAt("0.85")));

        assertThat(basket.totalPrice(), isTheAmountOf("29.83"));
    }

    private Matcher<BigDecimal> isTheAmountOf(String price) {
        return Matchers.is(moneyOf(price));
    }

}
