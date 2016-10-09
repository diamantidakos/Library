package com.mgiandia.library.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;

/**
 * Η κλάση αυτή δεν έχει σκοπό να ελέγξει κάποια λειτουργικότητα
 * του λογισμικού αλλά περισσότερο να επιδείξει διαφορετικού τύπου
 * ερωτήματα στη JPA-QL
 * @author Νίκος Διαμαντίδης
 *
 */
public class JPAQueriesTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }
    

    
    @SuppressWarnings("unchecked")
    @Test
    public void simpleQuery() {
        int EXPECTED_BOOK_NUMBER = 3;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select book from Book book");
        List<Book> results = query.getResultList();      
        Assert.assertEquals(EXPECTED_BOOK_NUMBER, results.size());
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void restrictionQuery() {
        int EXPECTED_NUMBER_STARTING_WITH_UML = 1;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select book from Book book where title like :titleCrit");
        query.setParameter("titleCrit", "UML%");
        List<Book> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_NUMBER_STARTING_WITH_UML,results.size());
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void implicitJoin() {
        int EXPECTED_ADDRESS_PATISSION = 0;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select b from Borrower b where address.street = 'Patission'");        
        List<Book> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_ADDRESS_PATISSION,results.size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void implicitJoin2() {
        int EXPECTED_ITEMS_STARTING_WITH_UML = 2;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select i from Item i where i.book.title like 'UML%'");        
        List<Book> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_ITEMS_STARTING_WITH_UML,results.size());        
    }
    
    @Test
    public void innerJoin() {
        int EXPECTED_ITEM_NUMBER = 5;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select i from Item i join i.book b");        
        Assert.assertEquals(EXPECTED_ITEM_NUMBER,query.getResultList().size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void outerJoin() {
        int EXPECTED_ITEM_NUMBER = 5;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select i from Item i left join i.book b");        
        @SuppressWarnings("unused")
        List<Book> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_ITEM_NUMBER,query.getResultList().size());
    }
}
