package ggd.webletter.model;

public class Person {

    private String name;
    private String address;

    @SuppressWarnings("unused")
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
