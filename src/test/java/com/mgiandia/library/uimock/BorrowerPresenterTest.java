package com.mgiandia.library.uimock;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.borrower.BorrowerPresenter;
import com.mgiandia.library.ui.borrower.BorrowerView;

public class BorrowerPresenterTest {
    private Initializer dataHelper;
    private BorrowerPresenter presenter;
    private BorrowerView mock;
    private Borrower borrower;
  
    @BeforeEach
    public void setUp() {
        borrower = new Borrower();
        borrower.setBorrowerNo(999);
        borrower.setLastName("kostas");
        borrower.setFirstName("karakostas");
        
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        
        mock = mock(BorrowerView.class);
        presenter = new BorrowerPresenter(mock);
        
        mock.setPresenter(presenter);
        mock.open();
    }
    
    @AfterEach
    public void tearDown() {
    	validateMockitoUsage();
    }
    
    @Test
    public void wiring() {
        presenter.start();
        verify(mock, atLeastOnce()).setBorrowerNo(anyInt());
        verify(mock, atLeastOnce()).setFirstName(null);
        verify(mock, atLeastOnce()).setLastName(null);
    }
    
    
    @Test
    public void setBorrowerAndSave() {
        
        when(mock.getBorrowerNo()).thenReturn(999);
        when(mock.getFirstName()).thenReturn("kostas");
        when(mock.getLastName()).thenReturn("karakostas");
    	
        
        EntityManager em = JPAUtil.createEntityManager();
        
        
        presenter.setBorrower(borrower);        
        presenter.setEntityManager(em);
        
        presenter.start();
        presenter.save();
        
        int allBorrowers = countBorrowers();
        Assertions.assertEquals(3, allBorrowers);
        
        verify(mock).close();        
    }

    
    @Test
    public void setBorrowerAndCancel() {
    	when(mock.getBorrowerNo()).thenReturn(999);
        when(mock.getFirstName()).thenReturn("nikos");
        when(mock.getLastName()).thenReturn("karanikos");
        
        
        presenter.setBorrower(borrower);  
        presenter.start();
        presenter.cancel();
        
        int allBorrowers = countBorrowers();
        Assertions.assertEquals(2, allBorrowers);
        
        verify(mock).close();        
    }
    
    @Test
    public void changeBorrowerInfoAndSave() {

    	when(mock.getBorrowerNo()).thenReturn(999);
        when(mock.getFirstName()).thenReturn("nikos");
        when(mock.getLastName()).thenReturn("karanikos");
    	
        EntityManager em = JPAUtil.createEntityManager();
        
        presenter.setBorrower(borrower);
        presenter.setEntityManager(em);
        presenter.start();
        presenter.save();
        
        
        Assertions.assertEquals("nikos", presenter.getBorrower().getFirstName());
        Assertions.assertEquals("karanikos", presenter.getBorrower().getLastName());
        
        int allBorrowers = countBorrowers();
        Assertions.assertEquals(3, allBorrowers);
        
        
    }
    
    
	private int countBorrowers() {
		EntityManager em;
		em = JPAUtil.createEntityManager();
        int allBorrowers = em.createQuery("select b from Borrower b")
        		.getResultList().size();
        
        em.close();
		return allBorrowers;
	}



}
