package com.li.strategy.improve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PayByCreditCard implements PayStrategy {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private CreditCard creditCard;

    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            creditCard.setAmount(creditCard.getAmount() - paymentAmount);
            return true;
        }
        return false;
    }

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Enter the card number: ");
            String number = br.readLine();
            System.out.print("Enter the card password: ");
            String password = br.readLine();
            creditCard = new CreditCard(number, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean cardIsPresent() {
        return creditCard != null;
    }
}
