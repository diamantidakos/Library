package com.mgiandia.library.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.loan.ReturnPresenter;
import com.mgiandia.library.util.SimpleCalendar;
import com.mgiandia.library.util.SystemDateStub;

public class ReturnPresenterTest {
    private Initializer dataHelper;
    private ReturnPresenter presenter;
    private ReturnViewStub returnView;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
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
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Borrower borrower = em.find(Borrower.class, Initializer.DIAMANTIDIS_ID); 
        Item item = em.find(Item.class,Initializer.UML_DISTILLED_ID1 );
        Loan loan = item.borrow(borrower);
        em.persist(loan);
        tx.commit();
        em.close();
    }
    
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(new SimpleCalendar(2007, 3, 1));
    }
    

    
    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(new SimpleCalendar(2007, 3, 30));
    }
}
