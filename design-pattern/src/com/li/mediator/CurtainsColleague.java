package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class CurtainsColleague extends Colleague {

    public CurtainsColleague(Mediator mediator, String name) {
        super(mediator, name);
        this.getMediator().register(name, this);
    }

    public void upCurtains() {
        System.out.println("收起幕布");
    }

    public void downCurtains() {
        System.out.println("拉下幕布");
    }

    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange, getName());
    }
}
