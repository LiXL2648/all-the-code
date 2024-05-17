package com.li.prototype.shallowclone;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class Sheep implements Cloneable {

    public String name;
    public int age;
    public String color;

    public Sheep friend;

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friend=" + friend +
                '}';
    }

    @Override
    public Sheep clone() {
        Sheep s = null;
        try {
            s = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return s;
    }
}
