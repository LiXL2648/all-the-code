package com.li.principle.segregation;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class Segregation2 {
    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.depend1(new B1());
        a1.depend2(new B1());
        a1.depend3(new B1());

        C1 c1 = new C1();
        c1.depend1(new D1());
        c1.depend4(new D1());
        c1.depend5(new D1());
    }
}

class A1 {
    public void depend1(Interface2 interface2) {
        interface2.operation1();
    }

    public void depend2(Interface3 interface3) {
        interface3.operation2();
    }

    public void depend3(Interface3 interface3) {
        interface3.operation3();
    }
}

class C1 {
    public void depend1(Interface2 interface2) {
        interface2.operation1();
    }

    public void depend4(Interface4 interface4) {
        interface4.operation4();
    }

    public void depend5(Interface4 interface4) {
        interface4.operation5();
    }
}

class B1 implements Interface2, Interface3 {
    @Override
    public void operation1() {
        System.out.println("B1实现了operation1方法");
    }

    @Override
    public void operation2() {
        System.out.println("B1实现了operation2方法");
    }

    @Override
    public void operation3() {
        System.out.println("B1实现了operation3方法");
    }
}

class D1 implements Interface2, Interface4 {
    @Override
    public void operation1() {
        System.out.println("D1实现了operation1方法");
    }

    @Override
    public void operation4() {
        System.out.println("D1实现了operation4方法");
    }

    @Override
    public void operation5() {
        System.out.println("D1实现了operation5方法");
    }
}

interface Interface2 {
    void operation1();
}

interface Interface3 {
    void operation2();
    void operation3();
}

interface Interface4 {
    void operation4();
    void operation5();
}
