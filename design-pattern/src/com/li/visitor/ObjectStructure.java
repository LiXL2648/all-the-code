package com.li.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {

    private final List<Person> persons = new LinkedList<>();

    public void attach(Person person) {
        persons.add(person);
    }

    public void detach(Person person) {
        persons.remove(person);
    }

    public void display(Action action) {
        persons.forEach(person -> person.accept(action));
    }
}
