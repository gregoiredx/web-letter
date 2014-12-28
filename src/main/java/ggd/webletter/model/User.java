package ggd.webletter.model;

import com.google.common.collect.Lists;

import javax.persistence.Id;
import java.util.List;

public class User {

    @Id
    private String name;
    List<Letter> letters;

    private User() {
    }

    public User(String name) {
        this.name = name;
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

}
