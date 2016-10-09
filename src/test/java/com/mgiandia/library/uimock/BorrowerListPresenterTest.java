package com.mgiandia.library.uimock;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.ViewRegistry;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;
import com.mgiandia.library.ui.borrower.BorrowerListView;
import com.mgiandia.library.ui.borrower.BorrowerPresenter;
import com.mgiandia.library.ui.borrower.BorrowerView;

public class BorrowerListPresenterTest {
    private Initializer dataHelper;
    private BorrowerListPresenter presenter;
    private BorrowerListView mock;
    private BorrowerView borrowerViewMock;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        mock = EasyMock.createMock(BorrowerListView.class);
        borrowerViewMock = EasyMock.createMock(BorrowerView.class);
        
        ViewRegistry.setBorrowerView(borrowerViewMock);
        
        presenter = new BorrowerListPresenter(mock);
        
        mock.setPresenter(presenter);
        mock.open();
    }
    
    @After
    public void tearDown() {
        ViewRegistry.reset();
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void wiring() {
        mock.setBorrowers((List<Borrower>) EasyMock.anyObject());
        EasyMock.replay(mock);
        presenter.start();
        EasyMock.verify(mock);
        Assert.assertEquals(2, presenter.getBorrowers().size());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void editSelected() {
 
    	Borrower selectedBorrower = new Borrower();
    	selectedBorrower.setBorrowerNo(999);
    	selectedBorrower.setLastName("karakostas");
    	selectedBorrower.setFirstName("kostas");
    	
        mock.setBorrowers((List<Borrower>) EasyMock.anyObject());
        
        EasyMock.expect(mock.getSelectedBorrower())
        	.andReturn(selectedBorrower);
        
        borrowerViewMock.setPresenter((BorrowerPresenter) EasyMock.anyObject());
        borrowerViewMock.open();
        borrowerViewMock.setBorrowerNo(selectedBorrower.getBorrowerNo());
        EasyMock.expectLastCall().anyTimes();
        borrowerViewMock.setFirstName(selectedBorrower.getFirstName());
        EasyMock.expectLastCall().anyTimes();
        borrowerViewMock.setLastName(selectedBorrower.getLastName());
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.replay(mock);
        EasyMock.replay(borrowerViewMock);
        
        presenter.start();
        presenter.editSelected();
        
        EasyMock.verify(mock);
        
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void add() {
        mock.setBorrowers((List<Borrower>) EasyMock.anyObject());
        
        borrowerViewMock.setPresenter((BorrowerPresenter) EasyMock.anyObject());
        borrowerViewMock.open();
        borrowerViewMock.setBorrowerNo(EasyMock.anyInt());
        EasyMock.expectLastCall().anyTimes();
        borrowerViewMock.setFirstName((String) EasyMock.anyObject());
        EasyMock.expectLastCall().anyTimes();
        borrowerViewMock.setLastName((String) EasyMock.anyObject());
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.replay(mock);
        EasyMock.replay(borrowerViewMock);
        
        presenter.start();
        presenter.addBorrower();
        
        EasyMock.verify(mock);
        
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void refresh() {
    	 mock.setBorrowers((List<Borrower>) EasyMock.anyObject());
    	 EasyMock.expectLastCall().anyTimes();
         EasyMock.replay(mock);
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
         
         EasyMock.verify(mock);
         
    }
}
