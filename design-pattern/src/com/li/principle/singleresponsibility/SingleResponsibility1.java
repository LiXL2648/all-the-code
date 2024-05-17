package com.li.principle.singleresponsibility;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("公车");
        vehicle.run("汽车");
        vehicle.run("火车");
    }
}

/**
 * 违反了单一职责原则
 * 根据交通工具运行方式不同，分解成不同类即可
 */
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公里上运行");
    }
}