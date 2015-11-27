package com.rafaelfiume.receipt.details.matchers;

import com.rafaelfiume.receipt.details.Product;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;

import static java.lang.String.format;

public class ProductMatcher extends TypeSafeMatcher<Product> {

    private final String name;
    private final BigDecimal priceWithTaxes;

    public ProductMatcher(String name, BigDecimal priceWithTaxes) {
        this.name = name;
        this.priceWithTaxes = priceWithTaxes;
    }

    public static ProductMatcher one(Product p) {
        return one(p.name(), p.priceWithTaxes());
    }

    public static ProductMatcher one(String name, BigDecimal priceWithTaxes) {
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
        mismatchDescription.appendText(format("product had name \"%s\" and priceWithTaxes \"%s\"", actual.name(), actual.priceWithTaxes()));
    }
}
