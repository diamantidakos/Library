package com.mgiandia.library.ui;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.borrower.BorrowerPresenter;

public class BorrowerPresenterTest {
    private Initializer dataHelper;
    private BorrowerPresenter presenter;
    private BorrowerViewStub borrowerView;
    private Borrower borrower;
    private BorrowerDAO borrowerDao;
  
    @Before
    public void setUp() {
        borrower = new Borrower();
        borrower.setBorrowerNo(999);
        borrower.setLastName("kostas");
        borrower.setFirstName("karakostas");
        
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        
        borrowerDao = DAOFactory.getFactory().getBorrowerDAO();
        
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
        
        presenter.setBorrower(borrower);        
        
        presenter.start();
        assertBorrower(borrower.getBorrowerNo(), 
        		borrower.getFirstName(),
        		borrower.getLastName());
        
        presenter.save();
        
        int allBorrowers = borrowerDao.findAll().size();
        Assert.assertEquals(3, allBorrowers);
    }

    
    @Test
    public void setBorrowerAndCancel() {
        presenter.setBorrower(borrower);  
        presenter.start();
        presenter.cancel();
        
        int allBorrowers = borrowerDao.findAll().size();
        Assert.assertEquals(2, allBorrowers);
    }
    
    @Test
    public void changeBorrowerInfoAndSave() {
        
    	borrower = borrowerDao.find(Initializer.DIAMANTIDIS_ID);
    	
    	
        presenter.setBorrower(borrower);
        presenter.start();
        
        borrowerView.setFirstName("nikos");
        borrowerView.setLastName("karanikos");
        
        presenter.save();
        
        borrower = borrowerDao.find(Initializer.DIAMANTIDIS_ID);
        
        Assert.assertEquals("nikos", borrower.getFirstName());
        Assert.assertEquals("karanikos", borrower.getLastName());
        
        
    }
    

    private void assertBorrower(int borrowerNo, String firstName, String lastName) {
    	Assert.assertEquals(borrowerNo, borrowerView.getBorrowerNo());
    	Assert.assertEquals(firstName, borrowerView.getFirstName());
    	Assert.assertEquals(lastName, borrowerView.getLastName());
    }

}
