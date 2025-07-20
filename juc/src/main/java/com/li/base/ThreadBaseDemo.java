package com.li.base;

public class ThreadBaseDemo {

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println("hello world");
            }
        }).start();
    }
}
