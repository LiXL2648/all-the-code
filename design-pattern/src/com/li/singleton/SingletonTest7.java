package com.li.singleton;

public class SingletonTest7 {
    public static void main(String[] args) {
        Singleton7 instance = Singleton7.getInstance();
        Singleton7 instance1 = Singleton7.getInstance();
        System.out.println(instance1 == instance);
    }
}

class Singleton7 {

    private Singleton7() {

    }

    private static class SingletonInstance {
        private static final Singleton7 instance = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.instance;
    }
}
