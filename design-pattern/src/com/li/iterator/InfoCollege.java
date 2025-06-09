package com.li.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoCollege implements College {

    private final List<Department> departments = new ArrayList<>();

    @Override
    public String getName() {
        return "信息科学学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        this.departments.add(new Department(name, desc));
    }

    @Override
    public Iterator<Department> createIterator() {
        return new InfoCollegeIterator(this.departments);
    }
}
