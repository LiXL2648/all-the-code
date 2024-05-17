package com.li.factory.factorymethod.logistics;

import com.li.factory.factorymethod.transport.TruckTransport;
import com.li.factory.factorymethod.transport.Transport;

/**
 * @author LiXL
 * @date 2024/1/11
 * 路上物流
 */
    public class RoadLogistics extends Logistics {

    @Override
    public Transport createTransport() {
        return new TruckTransport();
    }
}
