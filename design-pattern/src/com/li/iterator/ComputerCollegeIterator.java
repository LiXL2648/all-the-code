package com.li.iterator;

import java.util.Iterator;

public class ComputerCollegeIterator implements Iterator<Department> {

    private final Department[] departments;
    private int position;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        return !(position >= departments.length || departments[position] == null);
    }

    @Override
    public Department next() {
        return departments[position++];
    }
}
