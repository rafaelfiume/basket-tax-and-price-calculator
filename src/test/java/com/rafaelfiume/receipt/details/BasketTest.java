package com.rafaelfiume.receipt.details;

import com.rafaelfiume.receipt.details.matchers.ProductMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.rafaelfiume.receipt.details.ProductCategory.BOOK;
import static com.rafaelfiume.receipt.details.ProductCategory.FOOD;
import static com.rafaelfiume.receipt.details.ProductCategory.MEDIA;
import static com.rafaelfiume.receipt.details.matchers.ProductMatcher.one;
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

    private Product a(Product p) {
        return p;
    }

    private Product book() {
        return new Product("book", BOOK, "12.49");
    }

    private Product musicCD() {
        return new Product("music CD", MEDIA, "14.99");
    }

    private Product chocolateBar() {
        return new Product("chocolate bar", FOOD, "0.85");
    }
}
