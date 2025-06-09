package com.li.iterator;

import java.util.Iterator;

public class ComputerCollege implements College {

    private final Department[] departments;
    private int numOfDepartment;

    public ComputerCollege() {
        this.departments = new Department[5];
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        this.departments[numOfDepartment++] = new Department(name, desc);
    }

    @Override
    public Iterator<Department> createIterator() {
        return new ComputerCollegeIterator(this.departments);
    }
}
