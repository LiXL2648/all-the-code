package com.li.prototype.shape;

import java.util.Objects;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class Circle extends Shape {

    public int radius;

    public Circle() {
    }

    public Circle(Circle circle) {
        super(circle);
        if (circle != null) {
            this.radius = circle.radius;
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle && super.equals(o))) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius;
    }
}
