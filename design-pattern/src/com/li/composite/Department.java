package com.li.composite;

/**
 * @author LiXL
 * @date 2024/3/3
 */
public class Department extends Organization {

    public Department(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println("系：" + super.getName());
    }
}
