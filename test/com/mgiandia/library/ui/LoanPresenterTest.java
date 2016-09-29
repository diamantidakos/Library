package com.mgiandia.library.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.ui.loan.LoanPresenter;

public class LoanPresenterTest {
    private Initializer dataHelper;
    private LoanPresenter presenter;
    private LoanViewStub loanView;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
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
        
        Assert.assertFalse(presenter.isBorrowerFound());
        Assert.assertFalse(presenter.isItemFound());
        Assert.assertSame(loanView.getPresenter(), presenter);
        Assert.assertTrue(loanView.isOpened());
        Assert.assertFalse(loanView.isLoanActionEnabled());
    }
    
    
    @Test
    public void cancel() {       
        presenter.start();
        presenter.cancel();
        Assert.assertFalse(loanView.isOpened());
    }
    
    
    /**
     * Αναζήτηση δανειζομένου όταν αυτός δεν υπάρχει
     */
    @Test
    public void findBorrowerWhenIdDoesNotExist() {
        loanView.setBorrowerNo(4711);
        
        presenter.start();
        presenter.findBorrower();
        Assert.assertFalse(presenter.isBorrowerFound());        
        Assert.assertFalse(loanView.isLoanActionEnabled());
        Assert.assertEquals("", loanView.getBorrowerLastName());
        Assert.assertEquals("", loanView.getBorrowerFirstName());
        Assert.assertTrue(loanView.getErrorCount() > 0);
    }
    
    @Test
    public void findBorrowerWhenIdExists() {
        loanView.setBorrowerNo(Initializer.DIAMANTIDIS_ID);
        
        presenter.start();
        presenter.findBorrower();
        
        Assert.assertTrue(presenter.isBorrowerFound());
        Assert.assertEquals(presenter.getBorrower().getBorrowerNo(), Initializer.DIAMANTIDIS_ID);
        Assert.assertEquals("Διαμαντίδης", loanView.getBorrowerLastName());
        Assert.assertFalse(loanView.isLoanActionEnabled());
    }
    
    
    @Test
    public void findItemWhenIdDoesNotExist() {
    
        loanView.setItemNumber(4711);
        presenter.start();
        presenter.findItem();
        
        Assert.assertFalse(presenter.isItemFound());
        Assert.assertEquals("", loanView.getBookTitle());
        Assert.assertFalse(loanView.isLoanActionEnabled());
        Assert.assertTrue(loanView.getErrorCount() > 0 );
    }
    
    
    @Test
    public void findItemWhenIdExists() {

        loanView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        
        presenter.start();
        presenter.findItem();
        
        Assert.assertTrue(presenter.isItemFound());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getItem().getItemNumber());
        
        Assert.assertFalse("".equals(loanView.getBookTitle()));
        Assert.assertFalse(loanView.isLoanActionEnabled());
        
        
    }

    @Test
    public void performLoan() {       
        loanView.setBorrowerNo(Initializer.DIAMANTIDIS_ID);
        loanView.setItemNumber(Initializer.UML_DISTILLED_ID1);
               
        
        presenter.start();
        presenter.findBorrower();
        presenter.findItem();
        
        Assert.assertTrue(loanView.isLoanActionEnabled());
        
        presenter.borrowItem();
        
        Assert.assertEquals(1, presenter.getBorrower().getLoans().size());
        
        Assert.assertTrue(loanView.getInfoCount() > 0);
    }    
    
}
