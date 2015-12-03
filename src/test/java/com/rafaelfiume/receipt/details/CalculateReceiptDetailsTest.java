package com.rafaelfiume.receipt.details;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.List;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.rafaelfiume.receipt.details.ProductCategory.*;
import static com.rafaelfiume.receipt.details.ProductOrigin.IMPORTED;
import static com.rafaelfiume.receipt.details.matchers.MonetaryAmountMatchersFactory.is;
import static com.rafaelfiume.receipt.details.matchers.ProductMatcher.one;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpecRunner.class)
public class CalculateReceiptDetailsTest extends TestState implements WithCustomResultListeners {

    private final BasketManager basketManager = new TransientInMemoryBasketManager();

    @Notes("The specification says nothing about customers (more than one); it just mentions \"I\" :-)\n" +
            "Right now the app supports only *one* customer (both because I don't want to upset you guys implementing a feature that wasn't specified,\n" +
            "and because I'm in a hurry ;)\n" +
            "As a proactive developer though, I created a to-be-played story for handling more than one customer (see README.md)")
    @Test
    public void calculateTaxesAndTotalSalesPriceWhenCustomerBuysRegularItems() throws Exception {
        when(customerAddsToTheShoppingBasket(aBookAt("12.49"), aMusicCdAt("14.99"), and(aChocolateBarAt("0.85"))));

        then(theShoppingBasket(), lists(
                one("book", at("12.49")),
                one("music CD", at("16.49")),
                and(one("chocolate bar", at("0.85"))))
        );
        and(theShoppingBasket(), hasSize(3));

        and(basketTotalTaxes(), is("1.50"));
        and(basketTotalPrice(), is("29.83"));
    }

    @Test
    public void calculateTaxesAndTotalSalesPriceWhenCustomerBuysImportedItems() throws Exception {
        when(customerAddsToTheShoppingBasket(anImportedBoxOfChocolatesAt("10.00"), and(anImportedBottleOfPerfumeAt("47.50"))));

        then(theShoppingBasket(), lists(
                one("imported box of chocolates", at("10.50")),
                and(one("imported bottle of perfume", at("54.65"))))
        );
        and(theShoppingBasket(), hasSize(2));

        and(basketTotalTaxes(), is("7.65"));
        and(basketTotalPrice(), is("65.15"));
    }

    @Test
    public void calculateTaxesAndTotalSalesPriceWhenCustomerBuysNationalAndImportedItems() throws Exception {
        when(customerAddsToTheShoppingBasket(
                anImportedBottleOfPerfumeAt("27.99"),
                aBottleOfPerfumeAt("18.99"),
                aPacketOfHeadachePills("9.75"),
                and(anImportedBoxOfChocolatesAt("11.25"))));

        then(theShoppingBasket(), lists(
                one("imported bottle of perfume", at("32.19")),
                one("bottle of perfume", at("20.89")),
                one("packet of headache pills", at("9.75")),
                and(one("imported box of chocolates", at("11.85"))))
        );
        and(theShoppingBasket(), hasSize(4));

        and(basketTotalTaxes(), is("6.70"));
        and(basketTotalPrice(), is("74.68"));
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return sequence(
                new HtmlResultRenderer().
                        withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows()).
                        withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer()),
                new HtmlIndexRenderer()).
                safeCast(SpecResultListener.class);
    }

    private ActionUnderTest customerAddsToTheShoppingBasket(Product... products) {
        return (interestingGivens1, capturedInputAndOutputs1) -> {
            addToBasketTheFollowingProducts(products);
            return capturedInputAndOutputs1;
        };
    }

    private void addToBasketTheFollowingProducts(Product... products) {
        for (Product p : products) {
            basketManager.add(p);
        }
    }

    private StateExtractor<List<Product>> theShoppingBasket() {
        return capturedInputAndOutputs1 -> basketManager.selectedProducts();
    }

    private StateExtractor<BigDecimal> basketTotalTaxes() {
        return capturedInputAndOutputs1 -> basketManager.totalTaxes();
    }

    private StateExtractor<BigDecimal> basketTotalPrice() {
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
        return new Product("imported box of chocolates", FOOD, IMPORTED, price);
    }

    private Product anImportedBottleOfPerfumeAt(String price) {
        return new Product("imported bottle of perfume", PERFUME, IMPORTED, price);
    }

    private Product aBottleOfPerfumeAt(String price) {
        return new Product("bottle of perfume", PERFUME, price);
    }

    private Product aPacketOfHeadachePills(String price) {
        return new Product("packet of headache pills", MEDICINE, price);

    }

    private BigDecimal at(String price) {
        return MoneyDealer.moneyOf(price);
    }

    private Product and(Product p) {
        return p;
    }

    private <T> TestState and(StateExtractor<T> extractor, Matcher<? super T> matcher) throws Exception {
        return then(extractor, matcher);
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
