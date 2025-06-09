package com.li.strategy.improve;

public interface PayStrategy {

    boolean pay(int paymentAmount);

    void collectPaymentDetails();
}
