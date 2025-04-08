package com.li.mediator;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class Client {

    public static void main(String[] args) {
         Mediator mediator = new ConcreteMediator();

        AlarmColleague alarmColleague = new AlarmColleague(mediator, "alarmColleague");
        CoffeeMachineColleague coffeeMachineColleague = new CoffeeMachineColleague(mediator, "coffeeMachineColleague");
        CurtainsColleague curtainsColleague = new CurtainsColleague(mediator, "curtainsColleague");
        TVColleague tvColleague = new TVColleague(mediator, "tvColleague");

        alarmColleague.sendAlarm(0);
        System.out.println("--------------");
        coffeeMachineColleague.finishCoffee();
        System.out.println("--------------");
        alarmColleague.sendAlarm(1);
    }
}
