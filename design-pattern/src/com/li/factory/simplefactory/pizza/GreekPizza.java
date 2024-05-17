package com.li.factory.simplefactory.pizza;

public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        setPizzaName("希腊披萨");
        System.out.println(pizzaName + " preparing");
    }
}
