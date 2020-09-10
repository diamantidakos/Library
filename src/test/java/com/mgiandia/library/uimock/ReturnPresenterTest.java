package com.mgiandia.library.uimock;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.validateMockitoUsage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.loan.ReturnPresenter;
import com.mgiandia.library.ui.loan.ReturnView;
import com.mgiandia.library.util.SystemDateStub;

public class ReturnPresenterTest {
    private Initializer dataHelper;
    private ReturnPresenter presenter;
    private ReturnView mock;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        mock = mock(ReturnView.class);
        presenter = new ReturnPresenter(mock);
       
    }

    @AfterEach
    public void restoreSystemDate() {
        SystemDateStub.reset();
        validateMockitoUsage();
    }
    
    
    
    @Test
    public void wiring() {  
       
        presenter.start();
        
        Assertions.assertFalse(presenter.isLoanFound());
        verify(mock).open();
    }
    
    
    @Test
    public void cancel() {
      
        presenter.start();
        presenter.cancel();
        verify(mock).close();
    }
    
    
    
    @Test
    public void findPendingLoanWhenIdDoesNotExist() {
        when(mock.getItemNumber()).thenReturn(4711);

        
        
        presenter.start();
        presenter.returnItem();
        
        Assertions.assertFalse(presenter.isLoanFound());
        verify(mock).showError(any(String.class));        
    }
    
    
    @Test
    public void returnItemNoFine() {
        when(mock.getItemNumber()).thenReturn(Initializer.UML_DISTILLED_ID1);
        
        loanUMLDistilledToDiamantidis();
        
        presenter.start();
        presenter.returnItem();
        
        Assertions.assertTrue(presenter.isLoanFound());
        Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        
        verify(mock).showInfo(any(String.class));
    }
    

    @Test
    public void returnItemWithFine() {
        when(mock.getItemNumber()).thenReturn(Initializer.UML_DISTILLED_ID1);
    
        
        setSystemDateTo1stMarch2007();
        loanUMLDistilledToDiamantidis();
        setSystemDateTo30thMarch2007();
        
        presenter.start();
        presenter.returnItem();
        
        
        Assertions.assertTrue(presenter.isLoanFound());
        Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, presenter.getLoan().getItem().getItemNumber());
        Assertions.assertTrue(presenter.getLoan().getOverdue() > 0);
        
        
        verify(mock).showInfo(any(String.class));
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
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    

    
    private void setSystemDateTo30thMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 30));
    }
}
