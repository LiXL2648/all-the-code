package com.li.adapter.classadapter;

public class SquarePegAdapter extends SquarePeg implements RoundPeg {

    public SquarePegAdapter(double width) {
        super(width);
    }

    @Override
    public double getRadius() {
        return (Math.sqrt(Math.pow((getWidth() / 2), 2) * 2));
    }
}
