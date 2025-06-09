package com.li.strategy.improve;

public class CreditCard {

    private int amount;

    private final String number;

    private final String password;

    public CreditCard(String number, String password) {
        this.amount = 100000;
        this.number = number;
        this.password = password;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
