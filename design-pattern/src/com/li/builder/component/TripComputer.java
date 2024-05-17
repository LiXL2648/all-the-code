package com.li.builder.component;

import com.li.builder.car.Car;

/**
 * 产品特征：控制系统
 */
public class TripComputer {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * 剩余油量
     */
    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    /**
     * 启动状态
     */
    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
