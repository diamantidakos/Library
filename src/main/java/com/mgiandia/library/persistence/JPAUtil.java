package com.mgiandia.library.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();

    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("library");
        }
        return emf;
    }
    
    
    public static EntityManager getCurrentEntityManager() {      
        EntityManager em = currentEntityManager.get();         
        if (em  == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            currentEntityManager.set(em);
        }
        return em;
    }
    
    public static EntityManager createEntityManager() {
    	
    	return getEntityManagerFactory().createEntityManager();
    }

    public static void transactional(Runnable runnable){
    	
    	EntityManager em = getCurrentEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	runnable.run();
    	
    	tx.rollback();
    	em.close();
    	
    }
    
}
