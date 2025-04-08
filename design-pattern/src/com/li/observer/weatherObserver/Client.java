package com.li.observer.weatherObserver;

/**
 * @author LiXL
 * @date 2024/12/8
 */
public class Client {

    public static void main(String[] args) {

        WeatherSubject weatherSubject = new WeatherSubject();
        CurrentObserver currentObserver = new CurrentObserver();
        weatherSubject.registerObserver(currentObserver);
        weatherSubject.setData(10, 20, 30);
    }
}
