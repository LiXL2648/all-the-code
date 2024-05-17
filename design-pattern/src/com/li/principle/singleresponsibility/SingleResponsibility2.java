package com.li.principle.singleresponsibility;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("公车");
        roadVehicle.run("汽车");

        RailVehicle railVehicle = new RailVehicle();
        railVehicle.run("火车");
    }
}

/**
 * 遵守单一职责原则
 * 但是这样做的改动很大，既将类分解，同时修改客户端
 * 直接修改 Vehicle 类，改动的代码会比较少
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公里上运行");
    }
}

class RailVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在铁轨上运行");
    }
}
