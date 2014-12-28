package ggd.webletter.model;


import javax.persistence.Id;

public class Closing {

    @Id
    private String text;

    private Closing() {
    }

    public Closing(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
