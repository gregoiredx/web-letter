package ggd.webletter.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

public class Salutation {

    private String text;

    private Salutation() {
    }

    public Salutation(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
