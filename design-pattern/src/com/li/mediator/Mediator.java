package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public abstract class Mediator {

    public abstract void register(String colleagueName, Colleague colleague);

    public abstract void getMessage(int statChange, String colleagueName);

    public abstract void sendMessage();
}
