package com.mgiandia.library.ui;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;


import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;

public class BorrowerListPresenterTest {
    private Initializer dataHelper;
    private BorrowerListPresenter presenter;
    private BorrowerListViewStub borrowerListViewStub;
    private BorrowerViewStub borrowerViewStub;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        borrowerListViewStub = new BorrowerListViewStub();
        borrowerViewStub = new BorrowerViewStub();
        
        ViewRegistry.setBorrowerView(borrowerViewStub);
        
        presenter = new BorrowerListPresenter(borrowerListViewStub);
        
        borrowerListViewStub.setPresenter(presenter);
        borrowerListViewStub.open();
    }
    
    @AfterEach
    public void tearDown() {
        ViewRegistry.reset();
    }
    
    
	@Test
    public void wiring() {
        presenter.start();
        Assertions.assertTrue(borrowerListViewStub.isOpened());
        Assertions.assertEquals(2, presenter.getBorrowers().size());
        Assertions.assertEquals(2, borrowerListViewStub.getBorrowers().size());
    }
    
	@Test
    public void editSelected() {
 
    	Borrower selectedBorrower = new Borrower();
    	selectedBorrower.setBorrowerNo(999);
    	selectedBorrower.setLastName("karakostas");
    	selectedBorrower.setFirstName("kostas");
    	
    	borrowerListViewStub.setSelectedBorrower(selectedBorrower);
            
        presenter.start();
        presenter.editSelected();
    
        Assertions.assertTrue(borrowerViewStub.isOpened());
        
        Assertions.assertEquals(selectedBorrower.getBorrowerNo(), 
        		borrowerViewStub.getBorrowerNo());
        
        Assertions.assertEquals(selectedBorrower.getFirstName(), 
        		borrowerViewStub.getFirstName());


        Assertions.assertEquals(selectedBorrower.getLastName(), 
        		borrowerViewStub.getLastName());

    }
    
    

	@Test
    public void add() {
        
        presenter.start();
        presenter.addBorrower();
        
        Assertions.assertTrue(borrowerViewStub.isOpened());
        Assertions.assertEquals(0, borrowerViewStub.getBorrowerNo());
        
    }
    
	@Test
    public void refresh() {

         presenter.start();
         Assertions.assertEquals(2, presenter.getBorrowers().size());
         
         EntityManager em = JPAUtil.createEntityManager();
         EntityTransaction tx = em.getTransaction();
         tx.begin();
         
         Borrower borrower = new Borrower();
         borrower.setBorrowerNo(999);
         borrower.setLastName("karakostas");
         borrower.setFirstName("kostas");
         
         
         em.persist(borrower);
         
         tx.commit();
         em.close();
         
         presenter.refresh();
         Assertions.assertEquals(3, presenter.getBorrowers().size());
         Assertions.assertEquals(3, borrowerListViewStub.getBorrowers().size());
         
    }
}
