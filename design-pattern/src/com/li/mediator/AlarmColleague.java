package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class AlarmColleague extends Colleague {

    public AlarmColleague(Mediator mediator, String name) {
        super(mediator, name);
        this.getMediator().register(name, this);
    }

    public void sendAlarm(int stateChange) {
        sendMessage(stateChange);
    }

    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange, getName());
    }
}
