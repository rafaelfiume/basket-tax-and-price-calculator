package com.rafaelfiume.receipt.details.matchers;

import com.rafaelfiume.receipt.details.Product;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.lang.String.format;

public class ProductMatcher extends TypeSafeMatcher<Product> {

    private final String name;
    private final String price;

    public ProductMatcher(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public static ProductMatcher one(Product p) {
        return one(p.name(), p.price());
    }

    public static ProductMatcher one(String name, String price) {
        return new ProductMatcher(name, price);
    }

    @Override
    protected boolean matchesSafely(Product actualProduct) {
        return name.equals(actualProduct.name())
                && price.equals(actualProduct.price());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("a product with name \"%s\" and price \"%s\"", name, price));
    }

    @Override
    protected void describeMismatchSafely(Product actual, Description mismatchDescription) {
        mismatchDescription.appendText(format("product had name \"%s\" and price \"%s\"", actual.name(), actual.price()));
    }
}
