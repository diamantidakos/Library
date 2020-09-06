package com.mgiandia.library.ui;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.loan.ReturnPresenter;
import com.mgiandia.library.util.SystemDateStub;

public class ReturnPresenterTest {
    private Initializer dataHelper;
    private ReturnPresenter presenter;
    private ReturnViewStub returnView;
    
    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        returnView = new ReturnViewStub();
        presenter = new ReturnPresenter(returnView);
    }

    @After
    public void restoreSystemDate() {
        SystemDateStub.reset();
    }
    
    
    
    @Test
    public void wiring() {  
        presenter.start();
        Assert.assertTrue(returnView.isOpened());
        Assert.assertSame(presenter, returnView.getPresenter());
        Assert.assertFalse(presenter.isLoanFound());
    }
    
    
    @Test
    public void cancel() {
        presenter.start();
        presenter.cancel();
        Assert.assertFalse(returnView.isOpened());
    }
    
    
    
    @Test
    public void findPendingLoanWhenIdDoesNotExist() {
    	returnView.setItemNumber(4711);
        presenter.start();
        presenter.returnItem();
        
        Assert.assertFalse(presenter.isLoanFound());
        Assert.assertTrue(returnView.getErrorCount() > 0);
    }
    
    
    @Test
    public void returnItemNoFine() {
        loanUMLDistilledToDiamantidis();

    	returnView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        presenter.start();
        presenter.returnItem();
        
        Assert.assertTrue(presenter.isLoanFound());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
    }
    

    @Test
    public void returnItemWithFine() {
        setSystemDateTo1stMarch2007();
        loanUMLDistilledToDiamantidis();
        setSystemDateTo30thMarch2007();
        returnView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        
        presenter.start();
        presenter.returnItem();
        
        
        Assert.assertTrue(presenter.isLoanFound());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        Assert.assertTrue(presenter.getLoan().getOverdue() > 0);
        
    }

    
    private void loanUMLDistilledToDiamantidis() {
    	BorrowerDAO borrowerDao = new BorrowerDAOMemory();
    	ItemDAO itemDao = new ItemDAOMemory();
    	LoanDAO loanDao = new LoanDAOMemory();
        Borrower borrower = borrowerDao.find(Initializer.DIAMANTIDIS_ID);
        Item item = itemDao.find(Initializer.UML_DISTILLED_ID1);
        Loan loan = item.borrow(borrower);
        loanDao.save(loan);
    }
    
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    

    
    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 30));
    }
}
