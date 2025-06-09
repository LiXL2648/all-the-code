package com.li.strategy.improve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PayByPayPal implements PayStrategy {

    public static final Map<String, String> DATA_BASE = new HashMap<>();

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private String email;

    private String password;

    private boolean signedIn;

    static {
        DATA_BASE.put("lixl@qq.com", "123456");
        DATA_BASE.put("yucx@qq.com", "456789");
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Paying " + paymentAmount + " using PayPal.");
            return true;
        }
        return false;
    }

    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.print("Enter the user's email: ");
                email = br.readLine();
                System.out.print("Enter the password: ");
                password = br.readLine();
                if (verify()) {
                    System.out.println("Data verification has been successful.");
                } else {
                    System.out.println("Wrong email or password!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verify() {
        this.signedIn = password.equals(DATA_BASE.get(email));
        return signedIn;
    }
}
