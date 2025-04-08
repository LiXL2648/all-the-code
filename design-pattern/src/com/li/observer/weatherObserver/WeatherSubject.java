package com.li.observer.weatherObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXL
 * @date 2024/12/8
 */
public class WeatherSubject implements Subject {
    private float temperature;
    private float pressure;
    private float humidity;
    private final List<Observer> observers;

    public WeatherSubject() {
        this.observers = new ArrayList<>();
    }

    public void setData(float temperature, float pressure, float humidity) {
        changeData(temperature, pressure, humidity);
        notifyObservers();
    }

    public void changeData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(temperature, pressure, humidity);
        }
    }
}
