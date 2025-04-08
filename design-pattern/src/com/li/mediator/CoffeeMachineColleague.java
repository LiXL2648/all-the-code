package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class CoffeeMachineColleague extends Colleague {

    public CoffeeMachineColleague(Mediator mediator, String name) {
        super(mediator, name);
        this.getMediator().register(name, this);
    }

    public void startCoffee() {
        System.out.println("开始制作咖啡");
    }

    public void finishCoffee() {
        System.out.println("完成咖啡制作");
    }

    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange, getName());
    }
}
