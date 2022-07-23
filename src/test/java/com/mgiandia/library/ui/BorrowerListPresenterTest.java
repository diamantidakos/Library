package com.mgiandia.library.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.dao.BorrowerDAO;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;

public class BorrowerListPresenterTest {
    private Initializer dataHelper;
    private BorrowerListPresenter presenter;
    private BorrowerListViewStub borrowerListView;
    private BorrowerViewStub borrowerViewStub;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();        
        
        borrowerListView = new BorrowerListViewStub();
        borrowerViewStub = new BorrowerViewStub();
        
        ViewRegistry.setBorrowerView(borrowerViewStub);
        
        presenter = new BorrowerListPresenter(borrowerListView);
        
        borrowerListView.setPresenter(presenter);
        borrowerListView.open();
    }
    
    @AfterEach
    public void tearDown() {
        ViewRegistry.reset();
    }
    
    
	@Test
    public void wiring() {
        presenter.start();
        Assertions.assertTrue(borrowerListView.isOpened());
        Assertions.assertEquals(2, presenter.getBorrowers().size());
        Assertions.assertEquals(2, borrowerListView.getBorrowers().size());
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

         BorrowerDAO borrowerDao = new BorrowerDAOMemory();
         Borrower borrower = new Borrower();
         borrower.setBorrowerNo(999);
         borrower.setLastName("karakostas");
         borrower.setFirstName("kostas");
         
         borrowerDao.save(borrower);
         
         presenter.refresh();
         Assertions.assertEquals(3, presenter.getBorrowers().size());
         Assertions.assertEquals(3, borrowerListView.getBorrowers().size());
         
    }
}
