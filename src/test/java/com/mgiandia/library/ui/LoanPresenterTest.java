package com.mgiandia.library.ui;


import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.loan.LoanPresenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanPresenterTest {
    private Initializer dataHelper;
    private LoanPresenter presenter;
    private LoanViewStub loanView;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        loanView = new LoanViewStub();
        presenter = new LoanPresenter(loanView);
        loanView.setPresenter(presenter);
        loanView.open();
        loanView.setLoanActionEnabled(false);
    }
    

    
    @Test
    public void wiring() {  
        presenter.start();
        Assertions.assertFalse(presenter.isBorrowerFound());
        Assertions.assertFalse(presenter.isItemFound());
        Assertions.assertSame(loanView.getPresenter(), presenter);
        Assertions.assertTrue(loanView.isOpened());
        Assertions.assertFalse(loanView.isLoanActionEnabled());
    }
    
    
    @Test
    public void cancel() {       
        presenter.start();
        presenter.cancel();
        Assertions.assertFalse(loanView.isOpened());
    }
    
    
    /**
     * Αναζήτηση δανειζομένου όταν αυτός δεν υπάρχει
     */
    @Test
    public void findBorrowerWhenIdDoesNotExist() {
        loanView.setBorrowerNo(4711);
        presenter.start();
        presenter.findBorrower();
        Assertions.assertFalse(presenter.isBorrowerFound());        
        Assertions.assertFalse(loanView.isLoanActionEnabled());
        Assertions.assertEquals("", loanView.getBorrowerLastName());
        Assertions.assertEquals("", loanView.getBorrowerFirstName());
        Assertions.assertTrue(loanView.getErrorCount() > 0);
    }
    
    @Test
    public void findBorrowerWhenIdExists() {
        loanView.setBorrowerNo(Initializer.DIAMANTIDIS_ID);
        presenter.start();
        presenter.findBorrower();
        Assertions.assertTrue(presenter.isBorrowerFound());
        Assertions.assertEquals(presenter.getBorrower().getBorrowerNo(), Initializer.DIAMANTIDIS_ID);
        Assertions.assertEquals("Διαμαντίδης", loanView.getBorrowerLastName());
        Assertions.assertFalse(loanView.isLoanActionEnabled());
    }
    
    
    @Test
    public void findItemWhenIdDoesNotExist() {
        loanView.setItemNumber(4711);
        presenter.start();
        presenter.findItem();
        Assertions.assertFalse(presenter.isItemFound());
        Assertions.assertEquals("", loanView.getBookTitle());
        Assertions.assertFalse(loanView.isLoanActionEnabled());
        Assertions.assertTrue(loanView.getErrorCount() > 0 );
    }
    
    
    @Test
    public void findItemWhenIdExists() {
        loanView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        presenter.start();
        presenter.findItem();
        Assertions.assertTrue(presenter.isItemFound());
        Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getItem().getItemNumber());
        Assertions.assertFalse("".equals(loanView.getBookTitle()));
        Assertions.assertFalse(loanView.isLoanActionEnabled());
    }

    @Test
    public void performLoan() {       
        loanView.setBorrowerNo(Initializer.DIAMANTIDIS_ID);
        loanView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        
        presenter.start();
        presenter.findBorrower();
        presenter.findItem();
        Assertions.assertTrue(loanView.isLoanActionEnabled());
        
        presenter.borrowItem();
        Assertions.assertEquals(1, presenter.getBorrower().getLoans().size());
        Assertions.assertTrue(loanView.getInfoCount() > 0);
    }    
    
}
