package org.example.service;

import org.example.model.User;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserServiceTest {

    static EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    UserService userService;

    @BeforeClass
    public static void setUpEmf() {
        entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
    }

    @AfterClass
    public static void destroyEmf() {
        entityManagerFactory.close();
    }

    @Before
    public void setUpEm() {
        entityManager = entityManagerFactory.createEntityManager();
        userService = new UserService(entityManager);
        entityManager.getTransaction().begin();
        entityManager.createQuery("Select T from User T").getResultList().stream().forEach(o -> entityManager.remove(o));
        entityManager.getTransaction().commit();
    }

    @After
    public void destroyEm() {
        entityManager.close();
    }


    @Test
    public void testCrud() {
        List<User> l1 = entityManager.createQuery("Select T from User T", User.class).getResultList();
        Assert.assertEquals(0, l1.size());

        User user = new User();
        user.setEmail("Whatever");
        userService.save(user);

        Assert.assertEquals(user.getId(), userService.findById(user.getId()).getId());
        Assert.assertEquals(user.getEmail(), userService.findById(user.getId()).getEmail());

        userService.deleteById(user.getId());
        Assert.assertNull(userService.findById(user.getId()));

    }
}
