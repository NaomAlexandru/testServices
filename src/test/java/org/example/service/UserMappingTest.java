package org.example.service;

import org.example.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMappingTest extends AbstractTest {

    @Before
    public void cleaUpUserTable(){
        entityManager.createNativeQuery("delete from User").executeUpdate();
    }

    @Test
    public void testCrud() {

        List<User> users = entityManager.createQuery("select t from User t", User.class).getResultList();
        Assert.assertEquals(0, users.size());

        User user1 = new User();
        user1.setEmail("whatever");
        entityManager.persist(user1);
        entityManager.flush();
        entityManager.clear();

        User user2 = entityManager.find(User.class, user1.getId());
        Assert.assertEquals(user1.getEmail(), user2.getEmail());
        entityManager.clear();

        entityManager.remove(entityManager.find(User.class,user1.getId()));
        entityManager.flush();
        entityManager.clear();

        User user3 = entityManager.find(User.class, user1.getId());
        Assert.assertNull(user3);
    }
}
