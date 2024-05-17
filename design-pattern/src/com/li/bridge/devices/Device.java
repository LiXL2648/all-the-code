package com.li.bridge.devices;

/**
 * @author LiXL
 * @date 2024/2/2
 * 设备
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
