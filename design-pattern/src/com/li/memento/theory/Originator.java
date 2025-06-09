package com.li.memento.theory;

public interface Originator {
    void setState(String state);

    String getState();

    Memento saveMemento();

    void getStateFromMemento(Memento memento);
}
