package ggd.webletter.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

public class Person {

    private String name;
    private String address;

    private Person() {
    }

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
