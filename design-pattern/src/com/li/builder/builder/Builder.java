package com.li.builder.builder;

import com.li.builder.car.CarType;
import com.li.builder.component.Engine;
import com.li.builder.component.GPSNavigator;
import com.li.builder.component.Transmission;
import com.li.builder.component.TripComputer;

/**
 * 通用生成器接口
 */
public interface Builder {

    /**
     * 设置汽车类型
     */
    void setCarType(CarType type);

    /**
     * 设置汽车座位
     */
    void setSeats(int seats);

    /**
     * 设置汽车动力
     */
    void setEngine(Engine engine);

    /**
     * 挂档
     */
    void setTransmission(Transmission transmission);

    /**
     * 控制系统
     */
    void setTripComputer(TripComputer tripComputer);

    /**
     * GPS导航
     */
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
