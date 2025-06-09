package com.li.strategy.improve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Client {

    private static final Map<Integer, Integer> priceOnProducts = new HashMap<>();

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final Order order = new Order();

    private static PayStrategy payStrategy;

    static {
        priceOnProducts.put(1, 2000);
        priceOnProducts.put(2, 1500);
        priceOnProducts.put(3, 1000);
        priceOnProducts.put(4, 800);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(br.readLine());
                Integer cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(br.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = br.readLine();
            } while ("Y".equals(continueChoice));

            System.out.println("Please, select a payment method:" + "\n" +
                    "1 - PalPay" + "\n" +
                    "2 - Credit Card");
            String paymentMethod = br.readLine();
            if (paymentMethod.equals("1")) {
                payStrategy = new PayByPayPal();
            } else {
                payStrategy = new PayByCreditCard();
            }

            order.processOrder(payStrategy);
            System.out.print("Pay " + order.getTotalCost() + " units or Continue shopping? P/C: ");
            String proceed = br.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                // Finally, strategy handles the payment.
                if (payStrategy.pay(order.getTotalCost())) {
                    System.out.println("Payment has been successful.");
                } else {
                    System.out.println("FAIL! Please, check your data.");
                }
                order.setClosed();
            }
        }
    }
}
