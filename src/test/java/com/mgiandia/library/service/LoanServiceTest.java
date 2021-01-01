package com.mgiandia.library.service;

import java.util.List;

import org.junit.*;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.dao.*;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.MemoryInitializer;



public class LoanServiceTest {

    
    @Before
    public void setUp()  {        
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }


    @Test(expected=LibraryException.class)
    public void noBorrower() {
        LoanService loanService = new LoanService();
        loanService.findBorrower(99999);
        loanService.borrow(Initializer.UML_DISTILLED_ID1);
    }
    
    
    @Test
    public void testBorrow() {
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        
        LoanDAO loanDao = DAOFactory.getFactory().getLoanDAO();
        List<Loan> loanList= loanDao.findAll();
        Loan loan = loanList.get(0);
        
        Assert.assertTrue(loan.isPending());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
        Assert.assertEquals(ItemState.LOANED, loan.getItem().getState());
        
        
    }
    
    @Test
    public void borrowMemoryDataBase() {        
        LoanService loanService = new LoanService();
        loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
        
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_REFACTORING_ID));
        Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID2));
        Assert.assertNull(loanService.borrow(Initializer.UML_DISTILLED_ID2));    
    }    

}
