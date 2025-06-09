package com.li.proxy.dynamic;

import java.lang.reflect.Proxy;

public class TeacherDaoProxy {

    // 维护一个目标对象
    private final Object target;

    // 对 target 进行初始化
    public TeacherDaoProxy(Object target) {
        this.target = target;
    }

    // 给目标对象，生成一个代理对象
    public Object getProxyInstance() {

        // loader：指定当前目标对象使用的类加载器
        // interfaces：目标对象实现的接口类型，使用泛型的方式确认类型
        // InvocationHandler h：事情处理，执行目标对象的方法时，会去触发事情处理方法，会把
        // 当前执行的目标对象方法作为参数
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("JDK代理开始");
                    // 反射机制调用目标对象的方法
                    Object result = method.invoke(target, args);
                    System.out.println("JDK代理结束");
                    return result;
                });
    }
}
