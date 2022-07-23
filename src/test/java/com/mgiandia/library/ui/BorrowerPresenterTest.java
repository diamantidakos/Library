package com.mgiandia.library.ui;




import com.mgiandia.library.dao.BorrowerDAO;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.borrower.BorrowerPresenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BorrowerPresenterTest {
    private Initializer dataHelper;
    private BorrowerPresenter presenter;
    private BorrowerViewStub borrowerView;
    private Borrower borrower;
    private BorrowerDAO borrowerDao;
  
    @BeforeEach
    public void setUp() {
        borrower = new Borrower();
        borrower.setBorrowerNo(999);
        borrower.setLastName("kostas");
        borrower.setFirstName("karakostas");
        
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        
        borrowerDao = new BorrowerDAOMemory();
        
        borrowerView = new BorrowerViewStub();
        presenter = new BorrowerPresenter(borrowerView);
       
    }
    

    @Test
    public void wiring() {
    	 
        presenter.start();
        Assertions.assertSame(presenter, borrowerView.getPresenter());
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
        Assertions.assertEquals(3, allBorrowers);
    }

    
    @Test
    public void setBorrowerAndCancel() {
        presenter.setBorrower(borrower);  
        presenter.start();
        presenter.cancel();
        
        int allBorrowers = borrowerDao.findAll().size();
        Assertions.assertEquals(2, allBorrowers);
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
        
        Assertions.assertEquals("nikos", borrower.getFirstName());
        Assertions.assertEquals("karanikos", borrower.getLastName());
        
        
    }
    

    private void assertBorrower(int borrowerNo, String firstName, String lastName) {
    	Assertions.assertEquals(borrowerNo, borrowerView.getBorrowerNo());
    	Assertions.assertEquals(firstName, borrowerView.getFirstName());
    	Assertions.assertEquals(lastName, borrowerView.getLastName());
    }

}
