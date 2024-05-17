package com.li.factory.simplefactory.factoryOrder;

import com.li.factory.simplefactory.pizza.PepperPizza;
import com.li.factory.simplefactory.pizza.CheesePizza;
import com.li.factory.simplefactory.pizza.GreekPizza;
import com.li.factory.simplefactory.pizza.Pizza;

/**
 * @author LiXL
 * @date 2024/1/9
 */
public class SimpleFactory {

    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
