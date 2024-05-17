package com.li.composite;

/**
 * @author LiXL
 * @date 2024/3/3
 */
public abstract class Organization {

    private final String name;

    protected Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Organization organization) {
        throw new UnsupportedOperationException();
    }

    public void remove(Organization organization) {
        throw new UnsupportedOperationException();
    }

    public abstract void print();
}
