# basket-tax-and-price-calculator/ [![Build Status](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator.svg?branch=master)](https://travis-ci.org/rafaelfiume/basket-tax-and-price-calculator)

Demonstrates design and Tdd techniques calculating tax and total price for shopping baskets.

You can see the specification [here](http://rafaelfiume.github.io/basket-tax-and-price-calculator), generated from CalculateReceiptDetailsTest sour``ce code
and automatically updated when pushing code into master.

## Running the application locally (Requires Java 8)

Build with:

    $mvn clean install

## Tip:

CalculateReceiptDetailsTest is the acceptance test and is probably the best place to start looking at.

After building the app with maven, or running the test with an IDE, check out the generated spec at:

    file:///{a.temp.directory}/com/rafaelfiume/receipt/details/CalculateReceiptDetailsTest.html

The full path will be displayed in the console after => "Yatspec output:".

## User Stories

### ~~Calculate Basket Total Taxes and Price For *Non* Imported Items (Parent Story)~~
* ~~List items in the basket~~
* ~~Calculate taxes~~
* ~~Calculate total price~~

### ~~Calculate Basket Total Taxes and Price For Imported Items~~
* ~~Calculate taxes~~
* ~~Calculate total price~~

### ~~Calculate Basket Total Taxes and Price For Mixed Items - Imported or not~~
* ~~Calculate taxes~~
* ~~Calculate total price~~

### Supports More Than One Customer (Parent Story)