package com.li.memento.game;

public class Client {

    public static void main(String[] args) {

        Originator originator = new Originator();
        originator.setVit(100);
        originator.setDef(100);
        System.out.println("大战前：");
        originator.display();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        System.out.println("大战后：");
        originator.setVit(80);
        originator.setDef(80);
        originator.display();

        System.out.println("恢复到大战前：");
        originator.recoverFromMemento(caretaker.getMemento());
        originator.display();
    }
}
