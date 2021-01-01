package com.mgiandia.library.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SystemDateStub;



public class ReturnServiceTest {
    
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }

    @After
    public void restoreSystemDate() {
        SystemDateStub.reset();
    }
    
    @Test(expected=LibraryException.class)
    public void returnWhenNoLoanExist() {
        ReturnService service = new ReturnService();
        service.returnItem(2);
    }
    
    
    @Test
    public void confirmReturnedItem() {
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();        
        ReturnService service = new ReturnService();
        service.returnItem(Initializer.UML_USER_GUIDE_ID1); 
        LoanDAO loanDao = DAOFactory.getFactory().getLoanDAO();
        
        List<Loan> loanList = loanDao.findAll();                
        Loan loan = loanList.get(0);
        Assert.assertEquals(LocalDate.of(2007,3,1), loan.getLoanDate());
        Assert.assertEquals(LocalDate.of(2007,3,2), loan.getReturnDate());
        Assert.assertEquals(Initializer.UML_USER_GUIDE_ID1, loan.getItem().getItemNumber());
        Assert.assertEquals(ItemState.AVAILABLE, loan.getItem().getState());     
    }
    
    
    @Test
    public void returnNoFine(){
        setSystemDateTo1stMarch2007();
        borrowUMLUserGuideToDiamantidis();
        setSystemDateTo2ndMarch2007();
        ReturnService service = new ReturnService();
        Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
        Assert.assertNull(fine);
    }
    
    
    @Test
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
        service.findBorrower(2);
        service.borrow(1);
    }
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    

    private void setSystemDateTo2ndMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 2));
    }

    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 30));
    }

}
