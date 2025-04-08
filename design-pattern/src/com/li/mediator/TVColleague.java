package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class TVColleague extends Colleague {

    public TVColleague(Mediator mediator, String name) {
        super(mediator, name);
        this.getMediator().register(name, this);
    }

    public void startTV() {
        System.out.println("打开电视");
        sendMessage(0);
    }

    public void stopTV() {
        System.out.println("关掉电视");
        sendMessage(1);
    }

    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange, getName());
    }
}
