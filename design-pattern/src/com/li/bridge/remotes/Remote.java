package com.li.bridge.remotes;

import com.li.bridge.devices.Device;

/**
 * @author LiXL
 * @date 2024/2/2
 * 所有远程控制器的通用接口
 */
public abstract class Remote {

    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public abstract void power();

    public abstract void volumeDown();

    public abstract void volumeUp();

    public abstract void channelDown();

    public abstract void channelUp();
}
