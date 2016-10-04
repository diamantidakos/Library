package com.mgiandia.library.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;



public class LoanServiceTest {

    Initializer dataHelper;
    
    public void setUp() {                
        dataHelper.prepareData();
    }
 
    

    
    public void setUpJpa() {
        dataHelper = new Initializer();
        setUp();
    }
    
   
    
    @Test(expected=LibraryException.class)
    public void noBorrowerJpa() { 
        setUpJpa();
        LoanService loanService = new LoanService();
        loanService.findBorrower(99999);
        loanService.borrow(Initializer.UML_DISTILLED_ID1);    }
    

    
    @SuppressWarnings("unchecked")
	@Test
    public void testBorrowJpa() { 
        setUpJpa();
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        
        
        EntityManager em = JPAUtil.createEntityManager();
        List<Loan> loanList= em.createQuery("select l from Loan l").getResultList();
        
        Loan loan = loanList.get(0);

        Assert.assertTrue(loan.isPending());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
        Assert.assertEquals(ItemState.LOANED, loan.getItem().getState());
        
        em.close();
    }
    
 
    
    @Test
    public void borrowDataBaseJpa() { 
        setUpJpa();
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_REFACTORING_ID));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID2));
        Assert.assertNull(loanService.borrow(Initializer.UML_DISTILLED_ID2));    
    }
    
    

}
