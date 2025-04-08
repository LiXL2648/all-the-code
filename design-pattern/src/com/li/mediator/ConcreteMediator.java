package com.li.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiXL
 * @date 2024/12/12
 */
public class ConcreteMediator extends Mediator {

    private final Map<String, Colleague> colleagueMap;

    private final Map<String, String> interMap;

    public ConcreteMediator() {
        this.colleagueMap = new HashMap<>();
        this.interMap = new HashMap<>();
    }

    @Override
    public void register(String colleagueName, Colleague colleague) {
        this.colleagueMap.put(colleagueName, colleague);

        if (colleague instanceof AlarmColleague) {
            this.interMap.put("Alarm", colleagueName);
        } else if (colleague instanceof CoffeeMachineColleague) {
            this.interMap.put("CoffeeMachine", colleagueName);
        } else if (colleague instanceof TVColleague) {
            this.interMap.put("TV", colleagueName);
        } else if (colleague instanceof CurtainsColleague) {
            this.interMap.put("Curtains", colleagueName);
        }
    }

    @Override
    public void getMessage(int statChange, String colleagueName) {
        if (colleagueMap.get(colleagueName) instanceof AlarmColleague) {
            if (statChange == 0) {
                ((CoffeeMachineColleague) colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffee();
                ((TVColleague) colleagueMap.get(interMap.get("TV"))).startTV();
            } else if (statChange == 1) {
                ((TVColleague) colleagueMap.get(interMap.get("TV"))).stopTV();
            }
        } else if (colleagueMap.get(colleagueName) instanceof CurtainsColleague) {

        } else if (colleagueMap.get(colleagueName) instanceof CoffeeMachineColleague) {

        } else if (colleagueMap.get(colleagueName) instanceof TVColleague) {
            if (statChange == 0) {
                ((CurtainsColleague) colleagueMap.get(interMap.get("Curtains"))).downCurtains();
            } else {
                ((CurtainsColleague) colleagueMap.get(interMap.get("Curtains"))).upCurtains();
            }
        }
    }

    @Override
    public void sendMessage() {

    }
}
