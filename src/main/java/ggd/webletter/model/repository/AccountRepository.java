package ggd.webletter.model.repository;

import ggd.webletter.model.Account;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Account load(String username) {
        return entityManager.find(Account.class, username);
    }

    public void persist(Account account) {
        entityManager.persist(account);
    }

    public void remove(Account account) {
        entityManager.remove(account);
    }
}
