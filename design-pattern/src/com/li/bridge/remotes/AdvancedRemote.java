package com.li.bridge.remotes;

import com.li.bridge.devices.Device;

/**
 * @author LiXL
 * @date 2024/2/2
 * 高级远程控制器
 */
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
