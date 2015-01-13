package ggd.webletter.model;


import javax.persistence.Embeddable;
import javax.persistence.Id;

public class Closing {

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
