package com.li.flyweight;

/**
 * @author LiXL
 * @date 2024/3/12
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);
        Integer a = Integer.valueOf(128);
        Integer b = Integer.valueOf(128);
        System.out.println(x.equals(y)); // true
        System.out.println(x == y); // false
        System.out.println(x == z); // true
        System.out.println(w == x); // false
        System.out.println(w == y); // false
        System.out.println(a == b); // false
    }
}
