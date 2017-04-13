# basket-tax-and-price-calculator [![Build Status](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator.svg?branch=master)](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator) [![Apache 2.0 License](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://github.com/rafaelfiume/basket-tax-and-price-calculator/blob/master/LICENSE)
Demonstrates design and Tdd techniques calculating tax and total price for shopping baskets.

The basket-tax-and-price-calculator specification is available [here](http://rafaelfiume.github.io/basket-tax-and-price-calculator),
generated from [CalculateReceiptDetailsTest](https://github.com/rafaelfiume/basket-tax-and-price-calculator/blob/master/src/test/java/com/rafaelfiume/receipt/details/CalculateReceiptDetailsTest.java) source code and automatically updated when pushing code into master.

## Running the application locally (Requires Java 8)

Build with:

    $mvn clean install

## Tips:

* CalculateReceiptDetailsTest is the acceptance test and is probably the best place to start looking at.

* After building with maven or running the CalculateReceiptDetailsTest with an IDE, check out the generated spec at:

    file:///{a.temp.directory}/com/rafaelfiume/receipt/details/CalculateReceiptDetailsTest.html

The full path will be displayed in the console after => "Yatspec output:".
