package com.li.factory.factorymethod.logistics;

import com.li.factory.factorymethod.transport.ShipTransport;
import com.li.factory.factorymethod.transport.Transport;

/**
 * @author LiXL
 * @date 2024/1/11
 * 海上物流
 */
public class SeaLogistics extends Logistics {

    @Override
    public Transport createTransport() {
        return new ShipTransport();
    }
}
