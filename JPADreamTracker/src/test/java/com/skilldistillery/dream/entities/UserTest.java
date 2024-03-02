package com.skilldistillery.dream.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

class UserTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("JPADreamTracker");
    }
    
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        emf.close();
    }
    
    @BeforeEach
    void setUp() throws Exception {
        em = emf.createEntityManager();
    }
    
    @AfterEach
    void tearDown() throws Exception {
        em.close();
    }
    
    @Test
    void testUserMappings() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users = query.getResultList();
        
        assertNotNull(users);
        assertEquals(3, users.size());
        
        
        for (User user : users) {
            assertNotNull(user.getId());
            assertNotNull(user.getUsername());
            assertNotNull(user.getPassword());
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertNotNull(user.getEmail());
            assertNotNull(user.getBirthday());
            assertNotNull(user.getRole());
            assertNotNull(user.getDreams());
        }
    }
    
    @Test
    void testAssignZodiacSign_January19() {
        User user = new User();
        user.setBirthday(LocalDate.of(2000, Month.JANUARY, 19));
        user.assignZodiacSign();
        
        user.setZodiac(new Zodiac());
        user.getZodiac().setZodiacSign(ZodiacSign.Capricorn);

        assertEquals(ZodiacSign.Capricorn, user.getZodiac().getZodiacSign());
    }


}