package com.mgiandia.library.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;
import com.mgiandia.library.util.SystemDateStub;



public class ReturnServiceTest {
    
    private Initializer dataHelper ;
    

    @After
    public void restoreSystemDate() {
        SystemDateStub.reset();
        
    }
    
    
    public void setUp() {     
        dataHelper.prepareData();
    }
   
 
    
    public void setUpJpa() {

        dataHelper = new Initializer();
        setUp();
    }
    
    

   
    
    @Test(expected=LibraryException.class)
    public void returnWhenNoLoanExistJpa() { 
        setUpJpa();
        returnWhenNoLoanExist();       
    }

    
    
    public void returnWhenNoLoanExist() {
        ReturnService service = new ReturnService();
        service.returnItem(2);
    }
    

    
    @Test
    public void confirmReturnedItemJpa() { 
        setUpJpa();
        confirmReturnedItem();       
    }
    
    
    
    public void confirmReturnedItem() {
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();        
        ReturnService service = new ReturnService();
        service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        
        EntityManager em = JPAUtil.createEntityManager();
        
        @SuppressWarnings("unchecked")
		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();                
        Loan loan = loanList.get(0);
        Assert.assertEquals(new SimpleCalendar(2007,3,1), loan.getLoanDate());
        Assert.assertEquals(new SimpleCalendar(2007,3,2), loan.getReturnDate());
        Assert.assertEquals(Initializer.UML_USER_GUIDE_ID1, loan.getItem().getItemNumber());
        Assert.assertEquals(ItemState.AVAILABLE, loan.getItem().getState());     
        em.close();
    }
    

    
    @Test
    public void returnNoFineJpa() { 
        setUpJpa();
        returnNoFine();       
    }
    

    public void returnNoFine(){
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();
        ReturnService service = new ReturnService();
        Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        Assert.assertNull(fine);
    }
    

    
    @Test
    public void returnWithFineJpa() { 
        setUpJpa();
        returnNoFine();       
    }
    
    
    public void returnWithFine(){
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo30thMarch2007();
        ReturnService service = new ReturnService();
        Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        Assert.assertNotNull(fine);
    }
    
    
    private void borrowUMLUserGuideToDiamantidis() {
        LoanService service = new LoanService();
        service.findBorrower(Initializer.DIAMANTIDIS_ID);
        service.borrow(Initializer.UML_USER_GUIDE_ID1);
    }
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(new SimpleCalendar(2007, 3, 1));
    }
    

    private void setSystemDateTo2ndMarch2007() {        
        SystemDateStub.setStub(new SimpleCalendar(2007, 3, 2));
    }

    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(new SimpleCalendar(2007, 3, 30));
    }

}
