package com.li.uml.aggregation;

/**
 * @author LiXL
 * @date 2024/1/9
 */
public class Computer {

    private Mouse mouse;

    private Monitor monitor;

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
