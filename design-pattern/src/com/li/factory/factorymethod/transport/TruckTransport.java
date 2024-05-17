package com.li.factory.factorymethod.transport;

/**
 * @author LiXL
 * @date 2024/1/11
 * 卡车
 */
public class TruckTransport implements Transport {

    @Override
    public void driver() {
        System.out.println("卡车运输");
    }
}
