package com.li.factory.simplefactory.pizza;

public abstract class Pizza {
    protected String pizzaName;

    public abstract void prepare();

    public void bake() {
        System.out.println(pizzaName + " baking");
    }

    public void cut() {
        System.out.println(pizzaName + " cutting");
    }

    public void box() {
        System.out.println(pizzaName + " boxing");
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }
}
