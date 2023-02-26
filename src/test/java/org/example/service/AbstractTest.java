package org.example.service;
import org.junit.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class AbstractTest {

    static EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

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
        entityManager.getTransaction().begin();
    }

    @After
    public void destroyEm() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
