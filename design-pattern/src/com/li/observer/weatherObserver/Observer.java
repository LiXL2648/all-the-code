package com.li.observer.weatherObserver;

/**
 * @author LiXL
 * @date 2024/12/8
 */
public interface Observer {

    void update(float temperature, float pressure, float humidity);
}
