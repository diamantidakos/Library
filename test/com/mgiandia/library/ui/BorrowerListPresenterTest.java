package com.mgiandia.library.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;

public class BorrowerListPresenterTest {
    private Initializer dataHelper;
    private BorrowerListPresenter presenter;
    private BorrowerListViewStub borrowerListView;
    private BorrowerViewStub borrowerViewStub;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        borrowerListView = new BorrowerListViewStub();
        borrowerViewStub = new BorrowerViewStub();
        
        ViewRegistry.setBorrowerView(borrowerViewStub);
        
        presenter = new BorrowerListPresenter(borrowerListView);
        
        borrowerListView.setPresenter(presenter);
        borrowerListView.open();
    }
    
    @After
    public void tearDown() {
        ViewRegistry.reset();
    }
    
    
	@Test
    public void wiring() {
        presenter.start();
        Assert.assertTrue(borrowerListView.isOpened());
        Assert.assertEquals(2, presenter.getBorrowers().size());
        Assert.assertEquals(2, borrowerListView.getBorrowers().size());
    }
    
	@Test
    public void editSelected() {
 
    	Borrower selectedBorrower = new Borrower();
    	selectedBorrower.setBorrowerNo(999);
    	selectedBorrower.setLastName("karakostas");
    	selectedBorrower.setFirstName("kostas");
    	
    	borrowerListView.setSelectedBorrower(selectedBorrower);
            
        presenter.start();
        presenter.editSelected();
    
        Assert.assertTrue(borrowerViewStub.isOpened());
        
        Assert.assertEquals(selectedBorrower.getBorrowerNo(), 
        		borrowerViewStub.getBorrowerNo());
        
        Assert.assertEquals(selectedBorrower.getFirstName(), 
        		borrowerViewStub.getFirstName());


        Assert.assertEquals(selectedBorrower.getLastName(), 
        		borrowerViewStub.getLastName());

    }
    
    

	@Test
    public void add() {
        
        presenter.start();
        presenter.addBorrower();
        
        Assert.assertTrue(borrowerViewStub.isOpened());
        Assert.assertEquals(0, borrowerViewStub.getBorrowerNo());
        
    }
    
	@Test
    public void refresh() {

         presenter.start();
         Assert.assertEquals(2, presenter.getBorrowers().size());
         
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
         Assert.assertEquals(3, presenter.getBorrowers().size());
         Assert.assertEquals(3, borrowerListView.getBorrowers().size());
         
    }
}
