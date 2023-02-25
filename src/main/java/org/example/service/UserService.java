package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;

import javax.persistence.EntityManager;

@RequiredArgsConstructor

public class UserService {

    private final EntityManager em;

    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findById(Long id) {

        return em.find(User.class, id);
    }

    public void deleteById(Long id){
        em.getTransaction().begin();
        em.remove(em.getReference(User.class,id));
        em.getTransaction().commit();
    }

}
