package com.mgiandia.library.ui;

import java.time.LocalDate;



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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnPresenterTest {
    private Initializer dataHelper;
    private ReturnPresenter presenter;
    private ReturnViewStub returnView;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        returnView = new ReturnViewStub();
        presenter = new ReturnPresenter(returnView);
    }

    @AfterEach
    public void restoreSystemDate() {
        SystemDateStub.reset();
    }
    
    
    
    @Test
    public void wiring() {  
        presenter.start();
        Assertions.assertTrue(returnView.isOpened());
        Assertions.assertSame(presenter, returnView.getPresenter());
        Assertions.assertFalse(presenter.isLoanFound());
    }
    
    
    @Test
    public void cancel() {
        presenter.start();
        presenter.cancel();
        Assertions.assertFalse(returnView.isOpened());
    }
    
    
    
    @Test
    public void findPendingLoanWhenIdDoesNotExist() {
    	returnView.setItemNumber(4711);
        presenter.start();
        presenter.returnItem();
        
        Assertions.assertFalse(presenter.isLoanFound());
        Assertions.assertTrue(returnView.getErrorCount() > 0);
    }
    
    
    @Test
    public void returnItemNoFine() {
        loanUMLDistilledToDiamantidis();

    	returnView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        presenter.start();
        presenter.returnItem();
        
        Assertions.assertTrue(presenter.isLoanFound());
        Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
    }
    

    @Test
    public void returnItemWithFine() {
        setSystemDateTo1stMarch2007();
        loanUMLDistilledToDiamantidis();
        setSystemDateTo30thMarch2007();
        returnView.setItemNumber(Initializer.UML_DISTILLED_ID1);
        
        presenter.start();
        presenter.returnItem();
        
        
        Assertions.assertTrue(presenter.isLoanFound());
        Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        Assertions.assertTrue(presenter.getLoan().getOverdue() > 0);
        
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
