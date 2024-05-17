package com.li.adapter.classadapter;

public class Client {

    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);

        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(2);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(20);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
