package com.li.principle.segregation;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class Segregation1 {

    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());

        C c = new C();
        c.depend1(new D());
        c.depend4(new D());
        c.depend5(new D());
    }
}

/**
 * A 通过 Interface1 会依赖（使用）B
 * 但是 A 中只会使用到接口的 1，2，3 三个方法
 */
class A {
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend2(Interface1 interface1) {
        interface1.operation2();
    }

    public void depend3(Interface1 interface1) {
        interface1.operation3();
    }
}

/**
 * C 通过 Interface1 会依赖（使用）D
 * 但是 C 中只会使用到接口的 1，4，5 三个方法
 */
class C {
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend4(Interface1 interface1) {
        interface1.operation4();
    }

    public void depend5(Interface1 interface1) {
        interface1.operation5();
    }
}

class B implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("B实现了operation1方法");
    }

    @Override
    public void operation2() {
        System.out.println("B实现了operation2方法");
    }

    @Override
    public void operation3() {
        System.out.println("B实现了operation3方法");
    }

    @Override
    public void operation4() {
        System.out.println("B实现了operation4方法");
    }

    @Override
    public void operation5() {
        System.out.println("B实现了operation5方法");
    }
}

class D implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("D实现了operation1方法");
    }

    @Override
    public void operation2() {
        System.out.println("D实现了operation2方法");
    }

    @Override
    public void operation3() {
        System.out.println("D实现了operation3方法");
    }

    @Override
    public void operation4() {
        System.out.println("D实现了operation4方法");
    }

    @Override
    public void operation5() {
        System.out.println("D实现了operation5方法");
    }
}

interface Interface1 {
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}
