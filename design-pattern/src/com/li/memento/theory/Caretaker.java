package com.li.memento.theory;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private final List<Memento> mementos = new ArrayList<>();

    public void add(Memento memento) {
        this.mementos.add(memento);
    }

    public Memento get(int index) {
        return this.mementos.get(index);
    }
}
