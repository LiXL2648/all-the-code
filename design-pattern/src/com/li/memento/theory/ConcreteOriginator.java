package com.li.memento.theory;

public class ConcreteOriginator implements Originator {

    private String state;

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public Memento saveMemento() {
        return new ConcreteMemento(this.state);
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void getStateFromMemento(Memento memento) {
        this.state = memento.getState();
    }
}
