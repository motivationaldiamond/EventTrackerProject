package com.skilldistillery.dream.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class DreamTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Dream dream;

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
		dream = em.find(Dream.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dream = null;
	}
	
	@Test
    void test_Dream_entity_mapping() {
        assertNotNull(dream);
        assertEquals("Flying Dream", dream.getTitle());
        assertEquals(Emotion.Excitement, dream.getEmotion());
        assertEquals(Type.LucidDream, dream.getType());
    }
}
