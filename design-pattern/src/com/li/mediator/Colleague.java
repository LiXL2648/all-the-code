package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public abstract class Colleague {

    private final Mediator mediator;

    private final String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public String getName() {
        return name;
    }

    public abstract void sendMessage(int stateChange);
}
