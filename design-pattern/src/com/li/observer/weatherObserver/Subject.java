package com.li.observer.weatherObserver;

/**
 * @author LiXL
 * @date 2024/12/8
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
