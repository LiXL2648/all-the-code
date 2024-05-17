package com.li.singleton;

public class SingletonTest1 {

    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance1 == instance);
    }
}

// 饿汉式（静态变量）
class Singleton1 {

    // 1. 构造器私有化，外部不能new
    private Singleton1() {

    }

    // 2. 本类内部创建对象实例
    private static final Singleton1 instance = new Singleton1();

    // 3. 提供一个公有的静态方法，返回实例对象
    public static Singleton1 getInstance() {
        return instance;
    }
}