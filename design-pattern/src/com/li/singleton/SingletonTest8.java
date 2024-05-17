package com.li.singleton;

public class SingletonTest8 {

    public static void main(String[] args) {
        Singleton8 instance = Singleton8.INSTANCE;
        Singleton8 instance1 = Singleton8.INSTANCE;
        System.out.println(instance == instance1);
        instance.say();

        Runtime runtime = Runtime.getRuntime();
    }
}

enum Singleton8 {
    INSTANCE;

    public void say() {
        System.out.println("Hello World!");
    }
}
