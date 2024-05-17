package com.li.builder.house;

public class HighBuilding extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("给高级建筑打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("给高级建筑打砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给高级建筑打封顶");
    }
}
