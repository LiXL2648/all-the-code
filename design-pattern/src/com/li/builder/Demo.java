package com.li.builder;

import com.li.builder.builder.CarBuilder;
import com.li.builder.builder.CarManualBuilder;
import com.li.builder.car.Car;
import com.li.builder.car.Manual;
import com.li.builder.director.Director;

public class Demo {

    public static void main(String[] args) {

        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar(carBuilder);
        Car car = carBuilder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("Car manual built:\n" + carManual.print());
    }
}
