package com.rafaelfiume.receipt.details;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import com.rafaelfiume.receipt.details.matchers.ProductMatcher;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.rafaelfiume.receipt.details.matchers.ProductMatcher.one;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class CalculateReceiptDetailsTest extends TestState {

    private final BasketManager basketManager = new TransientInMemoryBasketManager();

    @Notes("The specification says nothing about customers (more than one); it just mentions \"I\" :-)\n" +
            "Right now the app supports only *one* customer (both because I don't want to upset you guys implementing a feature that wasn't specified,\n" +
            "and because I'm in a hurry ;)\n" +
            "As a proactive developer though, I created a to-be-played story for handling more than one customer (see README.md)")
    @Test
    public void calculateTaxesAndTotalSalesPriceWhenClientBuysItems() throws Exception {
        when(clientAddsToTheBasket(aBookAt("12.49"), aMusicCdAt("14.99"), and(aChocolateBarAt("0.85"))));

        then(theShoppingBasket(), lists(
                one("book", at("12.49")),
                one("music CD", at("14.99")),
                and(one("chocolate bar", at("0.85"))))
        );

        // coming soon
        //and(basketTotalTaxes(), is("1.50"));
        //and(basketTotalPrice(), is("28.83"));
    }

    private ActionUnderTest clientAddsToTheBasket(Product p, Product p1, Product p2) {
        return (interestingGivens1, capturedInputAndOutputs1) -> {
            addProductsToBasket(p, p1, p2);
            return capturedInputAndOutputs1;
        };
    }

    private void addProductsToBasket(Product... products) {
        for (Product p : products) {
            basketManager.add(p);
        }
    }

    private StateExtractor<List<Product>> theShoppingBasket() {
        return capturedInputAndOutputs1 -> basketManager.selectedProducts();
    }

    private StateExtractor<String> basketTotalTaxes() {
        return capturedInputAndOutputs1 -> basketManager.totalTaxes();
    }

    private StateExtractor<String> basketTotalPrice() {
        return capturedInputAndOutputs1 -> basketManager.totalPrice();
    }

    private Product aBookAt(String price) {
        return new Product("book", ProductCategory.BOOK, price);
    }

    private Product aMusicCdAt(String price) {
        return new Product("music CD", ProductCategory.MEDIA, price);
    }

    private Product aChocolateBarAt(String price) {
        return new Product("chocolate bar", ProductCategory.FOOD, price);
    }

    private String at(String price) {
        return price;
    }

    private Product and(Product p) {
        return p;
    }

    private TestState and(StateExtractor<String> stateExtractor, Matcher<String> matcher) throws Exception {
        return then(stateExtractor, matcher);
    }

    //
    //// Matchers
    //

    private Matcher<Product> and(Matcher<Product> matcher) {
        return matcher;
    }

    private Matcher<Iterable<? extends Product>> lists(Matcher<Product>... matchers) {
        return contains(matchers);
    }


}
