package com.mgiandia.library.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mgiandia.library.domain.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

/**
 * Η κλάση αυτή δεν έχει σκοπό να ελέγξει κάποια λειτουργικότητα
 * του λογισμικού αλλά περισσότερο να επιδείξει διαφορετικού τύπου
 * ερωτήματα στη JPA-QL
 * @author Νίκος Διαμαντίδης
 *
 */
@QuarkusTest
public class JPAQueriesTest {

	@Inject
	EntityManager em;

    
    @SuppressWarnings("unchecked")
    @Test
    @TestTransaction 
    public void simpleQuery() {
        int EXPECTED_BOOK_NUMBER = 3;
        Query query = em.createQuery("select book from Book book");
        List<Book> results = query.getResultList();      
        Assertions.assertEquals(EXPECTED_BOOK_NUMBER, results.size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    @TestTransaction 
    public void restrictionQuery() {
        int EXPECTED_NUMBER_STARTING_WITH_UML = 1;
        Query query = em.createQuery("select book from Book book where title like :titleCrit");
        query.setParameter("titleCrit", "UML%");
        List<Book> results = query.getResultList();  
        Assertions.assertEquals(EXPECTED_NUMBER_STARTING_WITH_UML,results.size());
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    @TestTransaction 
    public void implicitJoin() {
        int EXPECTED_ADDRESS_PATISSION = 0;
        Query query = em.createQuery("select b from Borrower b where address.street = 'Patission'");        
        List<Book> results = query.getResultList();  
        Assertions.assertEquals(EXPECTED_ADDRESS_PATISSION,results.size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    @TestTransaction 
    public void implicitJoin2() {
        int EXPECTED_ITEMS_STARTING_WITH_UML = 2;
        Query query = em.createQuery("select i from Item i where i.book.title like 'UML%'");        
        List<Book> results = query.getResultList();  
        Assertions.assertEquals(EXPECTED_ITEMS_STARTING_WITH_UML,results.size());        
    }
    
    @Test
    @TestTransaction 
    public void innerJoin() {
        int EXPECTED_ITEM_NUMBER = 5;
        Query query = em.createQuery("select i from Item i join i.book b");        
        Assertions.assertEquals(EXPECTED_ITEM_NUMBER,query.getResultList().size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void outerJoin() {
        int EXPECTED_ITEM_NUMBER = 5;
        Query query = em.createQuery("select i from Item i left join i.book b");        
        @SuppressWarnings("unused")
        List<Book> results = query.getResultList();  
        Assertions.assertEquals(EXPECTED_ITEM_NUMBER,query.getResultList().size());
    }
}
