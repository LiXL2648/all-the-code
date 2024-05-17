package com.li.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXL
 * @date 2024/3/3
 */
public class University extends Organization {

    private final List<Organization> list = new ArrayList<>();

    public University(String name) {
        super(name);
    }

    @Override
    public void add(Organization organization) {
        list.add(organization);
    }

    @Override
    public void remove(Organization organization) {
        list.remove(organization);
    }

    @Override
    public void print() {
        System.out.println("大学：" + super.getName());
        for (Organization organization : list) {
            organization.print();
        }
    }
}
