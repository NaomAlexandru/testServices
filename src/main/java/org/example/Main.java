package org.example;

import org.example.model.User;
import org.example.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user = new User();

        user.setEmail("NaomAlex");
        user.setName("Alex");
        user.setMobile("0773 982 296");

        UserService service = new UserService(entityManager);
       // service.save(user);
        User byId = service.findById(2L);
        System.out.println(byId);
        service.deleteById(2L);

        entityManager.close();
        entityManagerFactory.close();
    }
}