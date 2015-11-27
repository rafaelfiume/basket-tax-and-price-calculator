package com.rafaelfiume.receipt.details;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.List;

import static com.rafaelfiume.receipt.details.ProductCategory.*;
import static com.rafaelfiume.receipt.details.ProductOrigin.IMPORTED;
import static com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory.is;
import static com.rafaelfiume.receipt.details.matchers.ProductMatcher.one;
import static org.hamcrest.Matchers.contains;

@RunWith(SpecRunner.class)
public class CalculateReceiptDetailsTest extends TestState {

    private final BasketManager basketManager = new TransientInMemoryBasketManager();

    @Notes("The specification says nothing about customers (more than one); it just mentions \"I\" :-)\n" +
            "Right now the app supports only *one* customer (both because I don't want to upset you guys implementing a feature that wasn't specified,\n" +
            "and because I'm in a hurry ;)\n" +
            "As a proactive developer though, I created a to-be-played story for handling more than one customer (see README.md)")
    @Test
    public void calculateTaxesAndTotalSalesPriceWhenClientBuysRegularItems() throws Exception {
        when(clientAddsToTheBasket(aBookAt("12.49"), aMusicCdAt("14.99"), and(aChocolateBarAt("0.85"))));

        then(theShoppingBasket(), lists(
                one("book", at("12.49")),
                one("music CD", at("16.49")),
                and(one("chocolate bar", at("0.85"))))
        );

        and(basketTotalTaxes(), is("1.50"));
        and(basketTotalPrice(), is("29.83"));
    }

    @Test
    public void calculateTaxesAndTotalSalesPriceWhenClientBuysImportedItems() throws Exception {
        when(clientAddsToTheBasket(anImportedBoxOfChocolatesAt("10.00"), and(anImportedBottleOfPerfumeAt("47.50"))));

        then(theShoppingBasket(), lists(
                one("imported box of chocolate", at("10.50")),
                and(one("imported bottle of perfume", at("54.65"))))
        );

        and(basketTotalTaxes(), is("7.65"));
        and(basketTotalPrice(), is("65.15"));
    }

    private ActionUnderTest clientAddsToTheBasket(Product... products) {
        return (interestingGivens1, capturedInputAndOutputs1) -> {
            addProductsToBasketTheFollowingProducts(products);
            return capturedInputAndOutputs1;
        };
    }

    private void addProductsToBasketTheFollowingProducts(Product... products) {
        for (Product p : products) {
            basketManager.add(p);
        }
    }

    private StateExtractor<List<Product>> theShoppingBasket() {
        return capturedInputAndOutputs1 -> basketManager.selectedProducts();
    }

    private StateExtractor<MonetaryAmount> basketTotalTaxes() {
        return capturedInputAndOutputs1 -> basketManager.totalTaxes();
    }

    private StateExtractor<MonetaryAmount> basketTotalPrice() {
        return capturedInputAndOutputs1 -> basketManager.totalPrice();
    }

    private Product aBookAt(String price) {
        return new Product("book", BOOK, price);
    }

    private Product aMusicCdAt(String price) {
        return new Product("music CD", MEDIA, price);
    }

    private Product aChocolateBarAt(String price) {
        return new Product("chocolate bar", FOOD, price);
    }

    private Product anImportedBoxOfChocolatesAt(String price) {
        return new Product("imported box of chocolate", FOOD, IMPORTED, price);
    }

    private Product anImportedBottleOfPerfumeAt(String price) {
        return new Product("imported bottle of perfume", PERFUME, IMPORTED, price);
    }

    private MonetaryAmount at(String price) {
        return MoneyDealer.moneyOf(price);
    }

    private Product and(Product p) {
        return p;
    }

    private <T> TestState and(StateExtractor<T> monetaryAmountStateExtractor, Matcher<T> monetaryAmountMatcher) throws Exception {
        return then(monetaryAmountStateExtractor, monetaryAmountMatcher);
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
