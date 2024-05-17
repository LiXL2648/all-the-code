package com.li.factory.simplefactory.pizza;

public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        setPizzaName("奶酪披萨");
        System.out.println(pizzaName + " preparing");
    }
}
