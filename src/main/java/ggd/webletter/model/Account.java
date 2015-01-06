package ggd.webletter.model;

import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;

@Entity
public class Account {

    @Id
    private String name;

    private String credentials;

    @Transient
    List<Letter> letters;

    private Account() {
        // JPA
    }

    public Account(String name, String credentials) {
        this.name = name;
        this.credentials = credentials;
    }

    public void addLetter(Letter letter) {
        getLetters().add(letter);
    }

    public List<Letter> getLetters() {
        if (letters == null) {
            letters = Lists.newArrayList();
        }
        return letters;
    }

    public String getName() {
        return name;
    }

    public String getCredentials() {
        return credentials;
    }

}
