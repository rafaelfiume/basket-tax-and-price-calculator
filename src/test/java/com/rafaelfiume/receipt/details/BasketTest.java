package com.rafaelfiume.receipt.details;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.money.MonetaryAmount;
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
    public void shouleBeAbleToRetrieveAddedItemsToTheBasket() {
        // given
        basket.add(a(book())).add(a(musicCD())).add(a(chocolateBar()));

        // when
        final List<Product> selectedProducts = basket.products();

        // then
        assertThat(selectedProducts.size(), is(3));
        assertThat(selectedProducts, contains(one(book()), one(musicCD()), one(chocolateBar())));
    }

    @Test
    public void shouleCalculateTotalTaxesForRegularProductsInTheBasket() {
        basket.add(a(book())).add(a(musicCD())).add(a(chocolateBar()));

        assertThat(basket.totalTaxesToPay(), isTheAmountOf("1.50"));
    }

    @Test
    public void shouleCalculateTotalPriceForRegularProductsInTheBasket() {
        basket.add(a(book())).add(a(musicCD())).add(a(chocolateBar()));

        assertThat(basket.totalPrice(), isTheAmountOf("29.83"));
    }

    private Matcher<MonetaryAmount> isTheAmountOf(String price) {
        return Matchers.is(moneyOf(price));
    }

}
