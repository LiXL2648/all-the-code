package com.li.factory.simplefactory.factoryOrder;

public class OrderStore {

    public static void main(String[] args) {
        new Order(new SimpleFactory()).creatOrder();
    }
}
