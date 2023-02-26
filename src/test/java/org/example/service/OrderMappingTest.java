package org.example.service;

import org.example.model.Order;
import org.example.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderMappingTest extends AbstractTest {

    @Before
    public void cleaUpUserTable() {
        entityManager.createNativeQuery("delete from T_Order").executeUpdate();
        entityManager.createNativeQuery("delete from User").executeUpdate();
    }

    @Test
    public void testCru() {
        User user = new User();
        user.setEmail("whatever");
        entityManager.persist(user);


        {
            Order order = new Order();
            order.setId(1);
            order.setProductPrice(200);
            order.setProductName("Finetti");
            order.setProductQuantity(300);
            order.setUser(user);
            entityManager.persist(order);
            entityManager.flush();
        }
        {
            Order order = new Order();
            order.setId(2);
            order.setProductPrice(300);
            order.setProductName("Nutella");
            order.setProductQuantity(100);
            order.setUser(user);
            entityManager.persist(order);
            entityManager.flush();
        }

        entityManager.flush();
        entityManager.clear();
        User user1 = entityManager.find(User.class, user.getId());
        System.out.println(user1);
        System.out.println(user1.getOrders().size());

        List<Order> orders = entityManager.createQuery("select o from Order o where o.user.id = :id", Order.class).setParameter("id", user1.getId()).getResultList();
        System.out.println(orders);


    }
}
