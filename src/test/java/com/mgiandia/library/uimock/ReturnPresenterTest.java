package com.mgiandia.library.uimock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.easymock.EasyMock;
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
import com.mgiandia.library.ui.loan.ReturnView;
import com.mgiandia.library.util.SimpleCalendar;
import com.mgiandia.library.util.SystemDateStub;

public class ReturnPresenterTest {
    private Initializer dataHelper;
    private ReturnPresenter presenter;
    private ReturnView mock;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        mock = EasyMock.createMock(ReturnView.class);
        presenter = new ReturnPresenter(mock);
        mock.setPresenter(presenter);
        mock.open();
    }

    @After
    public void restoreSystemDate() {
        SystemDateStub.reset();
    }
    
    
    
    @Test
    public void wiring() {  
        EasyMock.replay(mock);
        presenter.start();
        
        Assert.assertFalse(presenter.isLoanFound());
        EasyMock.verify(mock);
    }
    
    
    @Test
    public void cancel() {
        mock.close();
        EasyMock.replay(mock);
        
        presenter.start();
        presenter.cancel();
        EasyMock.verify(mock);
    }
    
    
    
    @Test
    public void findPendingLoanWhenIdDoesNotExist() {
        EasyMock.expect(mock.getItemNumber()).andReturn(4711);
        mock.showError((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);
        
        presenter.start();
        presenter.returnItem();
        
        Assert.assertFalse(presenter.isLoanFound());
        EasyMock.verify(mock);        
    }
    
    
    @Test
    public void returnItemNoFine() {
        EasyMock.expect(mock.getItemNumber()).andReturn(Initializer.UML_DISTILLED_ID1);
        mock.showInfo((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);        
        loanUMLDistilledToDiamantidis();
        
        presenter.start();
        presenter.returnItem();
        
        Assert.assertTrue(presenter.isLoanFound());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        
        EasyMock.verify(mock);
    }
    

    @Test
    public void returnItemWithFine() {
        EasyMock.expect(mock.getItemNumber()).andReturn(Initializer.UML_DISTILLED_ID1);
        mock.showInfo((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);
        
        setSystemDateTo1stMarch2007();
        loanUMLDistilledToDiamantidis();
        setSystemDateTo30thMarch2007();
        
        presenter.start();
        presenter.returnItem();
        
        
        Assert.assertTrue(presenter.isLoanFound());
        Assert.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        Assert.assertTrue(presenter.getLoan().getOverdue() > 0);
        
        
        EasyMock.verify(mock);
    }

    
    private void loanUMLDistilledToDiamantidis() {
        
    	EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	
        Borrower borrower = em.find(Borrower.class, Initializer.DIAMANTIDIS_ID); 
        Item item = em.find(Item.class, Initializer.UML_DISTILLED_ID1);
        
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
