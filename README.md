# basket-tax-and-price-calculator [![Build Status](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator.svg?branch=master)](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator) [![Apache 2.0 License](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://github.com/rafaelfiume/basket-tax-and-price-calculator/blob/master/LICENSE)
An example of how to use BDD to generate an _executable specification_, to _design_ and _implement_ a component (in this case, a shopping basket).

The basket component specification is available [here](http://rafaelfiume.github.io/basket-tax-and-price-calculator),
generated from [CalculateReceiptDetailsTest.java](https://github.com/rafaelfiume/basket-tax-and-price-calculator/blob/master/src/test/java/com/rafaelfiume/receipt/details/CalculateReceiptDetailsTest.java) and automatically updated when pushing code into master.

Note that the executable specification here is generated from an unit test, not from an acceptance test. This is to show that it can be important to have this kind of documentation on a component level too.

## Running the application locally (Requires Java 8)

Build with:

    $mvn clean install

## Tips:

* CalculateReceiptDetailsTest is the acceptance test and is probably the best place to start looking at.

* After building with maven or running the CalculateReceiptDetailsTest with an IDE, check out the generated spec at:

    file:///{a.temp.directory}/com/rafaelfiume/receipt/details/CalculateReceiptDetailsTest.html

The full path will be displayed in the console after => "Yatspec output:".
