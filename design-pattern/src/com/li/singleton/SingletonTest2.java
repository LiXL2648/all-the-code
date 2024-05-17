package com.li.singleton;

public class SingletonTest2 {

    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance1 = Singleton2.getInstance();
        System.out.println(instance1 == instance);
    }
}

// 饿汉式（静态代码块）
class Singleton2 {

    // 1. 构造器私有化，外部不能new
    private Singleton2() {

    }

    // 2. 本类内部创建对象实例
    private static final Singleton2 instance;

    // 3. 静态代码块执行时，创建单例对象
    static {
        instance = new Singleton2();
    }

    // 4. 提供一个公有的静态方法，返回实例对象
    public static Singleton2 getInstance() {
        return instance;
    }
}