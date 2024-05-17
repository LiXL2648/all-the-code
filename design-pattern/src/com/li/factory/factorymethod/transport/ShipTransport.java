package com.li.factory.factorymethod.transport;

/**
 * @author LiXL
 * @date 2024/1/11
 * 卡车
 */
public class ShipTransport implements Transport {

    @Override
    public void driver() {
        System.out.println("轮船运输");
    }
}
