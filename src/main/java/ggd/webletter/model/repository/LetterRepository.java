package ggd.webletter.model.repository;

import ggd.webletter.model.Letter;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class LetterRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void persist(Letter letter) {
        entityManager.persist(letter);
    }
}
