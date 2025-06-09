package com.li.memento.theory;

public class Client {

    public static void main(String[] args) {

        Caretaker caretaker = new Caretaker();
        Originator originator = new ConcreteOriginator();
        originator.setState("状态1");
        caretaker.add(originator.saveMemento());
        originator.setState("状态2");
        caretaker.add(originator.saveMemento());
        originator.setState("状态3");
        caretaker.add(originator.saveMemento());

        System.out.println("当前状态是：" + originator.getState());
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("当前状态是：" + originator.getState());
    }
}
