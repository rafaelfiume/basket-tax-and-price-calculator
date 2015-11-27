package com.rafaelfiume.receipt.details.matchers;

import com.rafaelfiume.receipt.details.Product;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import javax.money.MonetaryAmount;

import static java.lang.String.format;

public class ProductMatcher extends TypeSafeMatcher<Product> {

    private final String name;
    private final MonetaryAmount priceWithTaxes;

    public ProductMatcher(String name, MonetaryAmount priceWithTaxes) {
        this.name = name;
        this.priceWithTaxes = priceWithTaxes;
    }

    public static ProductMatcher one(Product p) {
        return one(p.name(), p.priceWithTaxes());
    }

    public static ProductMatcher one(String name, MonetaryAmount priceWithTaxes) {
        return new ProductMatcher(name, priceWithTaxes);
    }

    @Override
    protected boolean matchesSafely(Product actualProduct) {
        return name.equals(actualProduct.name())
                && priceWithTaxes.equals(actualProduct.priceWithTaxes());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("a product with name \"%s\" and priceWithTaxes \"%s\"", name, priceWithTaxes));
    }

    @Override
    protected void describeMismatchSafely(Product actual, Description mismatchDescription) {
        mismatchDescription.appendText(format("product had name \"%s\" and priceWithTaxes \"%s\"", actual.name(), actual.price()));
    }
}
