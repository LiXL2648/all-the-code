package com.li.builder.house;

public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("给普通房子打砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给普通房子打封顶");
    }
}
