package com.mgiandia.library.ui;


import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.borrower.BorrowerPresenter;

public class BorrowerPresenterTest {
    private Initializer dataHelper;
    private BorrowerPresenter presenter;
    private BorrowerViewStub borrowerView;
    private Borrower borrower;
  
    @Before
    public void setUp() {
        borrower = new Borrower();
        borrower.setBorrowerNo(999);
        borrower.setLastName("kostas");
        borrower.setFirstName("karakostas");
        
        
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        
        borrowerView = new BorrowerViewStub();
        presenter = new BorrowerPresenter(borrowerView);
       
    }
    
    
    @Test
    public void wiring() {
    	 
        presenter.start();
        Assert.assertSame(presenter, borrowerView.getPresenter());
    }
    
    
    @Test
    public void setBorrowerAndSave() {
        EntityManager em = JPAUtil.createEntityManager();
    	
        presenter.setBorrower(borrower);     
        presenter.setEntityManager(em);
        
        presenter.start();
        assertBorrower(borrower.getBorrowerNo(), 
        		borrower.getFirstName(),
        		borrower.getLastName());
        
        presenter.save();
        
        em.close();
        
        int allBorrowers = countBorrowers();
        Assert.assertEquals(3, allBorrowers);
    }



    
    @Test
    public void setBorrowerAndCancel() {
        presenter.setBorrower(borrower);  
        presenter.start();
        presenter.cancel();
        
        int allBorrowers = countBorrowers();
        Assert.assertEquals(2, allBorrowers);
    }
    
    @Test
    public void changeBorrowerInfoAndSave() {
        EntityManager em = JPAUtil.createEntityManager();
        
        borrower = em.find(Borrower.class, Initializer.DIAMANTIDIS_ID);
        presenter.setBorrower(borrower);
        presenter.setEntityManager(em);
        
        presenter.start();
        
        borrowerView.setFirstName("nikos");
        borrowerView.setLastName("karanikos");
        
        presenter.save();
        
        em.close();
        
        em = JPAUtil.createEntityManager();
        
        borrower = em.find(Borrower.class, Initializer.DIAMANTIDIS_ID);
        
        Assert.assertEquals("nikos", borrower.getFirstName());
        Assert.assertEquals("karanikos", borrower.getLastName());
        
        
    }
    

    private void assertBorrower(int borrowerNo, String firstName, String lastName) {
    	Assert.assertEquals(borrowerNo, borrowerView.getBorrowerNo());
    	Assert.assertEquals(firstName, borrowerView.getFirstName());
    	Assert.assertEquals(lastName, borrowerView.getLastName());
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
