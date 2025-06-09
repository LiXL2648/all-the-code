package com.li.strategy.tradition;

public abstract class Duck {

    public Duck() {

    }

    public abstract void display();

    public void quack() {
        System.out.println("鸭会叫");
    }

    public void swim() {
        System.out.println("鸭会游泳");
    }

    public void fly() {
        System.out.println("鸭会飞");
    }
}
