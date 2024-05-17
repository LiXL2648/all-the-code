package com.li.factory.simplefactory.store;

import com.li.factory.simplefactory.pizza.PepperPizza;
import com.li.factory.simplefactory.pizza.CheesePizza;
import com.li.factory.simplefactory.pizza.GreekPizza;
import com.li.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Order {

    public Order() {
        Pizza pizza;
        do {
            String type = getType();
            if ("greek".equals(type)) {
                pizza = new GreekPizza();
            } else if ("cheese".equals(type)) {
                pizza = new CheesePizza();
            } else if ("pepper".equals(type)) {
                pizza = new PepperPizza();
            } else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    public String getType() {
        System.out.println("输入披萨名称：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
