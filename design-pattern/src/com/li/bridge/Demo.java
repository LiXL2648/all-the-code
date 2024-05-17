package com.li.bridge;

import com.li.bridge.devices.Device;
import com.li.bridge.devices.Radio;
import com.li.bridge.devices.Tv;
import com.li.bridge.remotes.AdvancedRemote;
import com.li.bridge.remotes.BasicRemote;

/**
 * @author LiXL
 * @date 2024/2/2
 */
public class Demo {

    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
