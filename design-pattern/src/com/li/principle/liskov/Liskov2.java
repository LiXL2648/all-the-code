package com.li.principle.liskov;

/**
 * @author LiXL
 * @date 2024/1/2
 */
public class Liskov2 {
    public static void main(String[] args) {
        C a = new C();
        System.out.println("11-3=" + a.func1(11, 3));
        System.out.println("1-8=" + a.func1(1, 8));
        System.out.println("-----------------------");
        D b = new D();
        System.out.println("11+3=" + b.func1(11, 3));
        System.out.println("1+8=" + b.func1(1, 8));
        System.out.println("11+3+9=" + b.func2(11, 3));
        System.out.println("-----------------------");
        System.out.println("11-3=" + b.func3(11, 3));
    }
}

class Base {

}

class C extends Base {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

class D extends Base {

    // 使用组合关系，在D中使用C的方法
    C c = new C();

    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.c.func1(a, b);
    }
}
