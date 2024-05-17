package com.li.factory.simplefactory.factoryOrder;

import com.li.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author LiXL
 * @date 2024/1/9
 */
public class Order {

    private final SimpleFactory simpleFactory;

    public Order(SimpleFactory simpleFactory) {
        this.simpleFactory = simpleFactory;
    }

    public void creatOrder() {
        do {
            String orderType = getOrderType();
            Pizza pizza = simpleFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败");
                break;
            }
        } while (true);
    }

    private String getOrderType() {
        System.out.println("输入披萨名称：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
