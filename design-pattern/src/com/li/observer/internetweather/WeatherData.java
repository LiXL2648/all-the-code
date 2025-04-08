package com.li.observer.internetweather;

public class WeatherData {

    private float temperature;
    private float pressure;
    private float humidity;
    private final CurrentConditions currentConditions;

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }

    public void dataChange() {
        currentConditions.update(this.temperature, this.pressure, this.humidity);
    }
}
