package com.li.factory.factorymethod.logistics;

import com.li.factory.factorymethod.transport.Transport;

/**
 * @author LiXL
 * @date 2024/1/11
 * 物流
 */
public abstract class Logistics {

    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.driver();
    }
}
