package com.li.principle.singleresponsibility;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle = new Vehicle2();
        vehicle.runRoad("公车");
        vehicle.runWater("轮船");
        vehicle.runRail("火车");
    }
}

/**
 * 这种修改方法没有对原来的类最大的修改，只是增加了方法
 * 虽然没有在类的级别上遵守单一职责原则，但是在方法上，仍然是遵守单一职责原则的
 */
class Vehicle2 {

    public void runRoad(String vehicle) {
        System.out.println(vehicle + "在公里上运行");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水上运行");
    }

    public void runRail(String vehicle) {
        System.out.println(vehicle + "在铁轨上运行");
    }
}
