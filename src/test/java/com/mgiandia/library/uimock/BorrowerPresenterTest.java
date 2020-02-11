package com.mgiandia.library.uimock;


import javax.persistence.EntityManager;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
  
    @Before
    public void setUp() {
        borrower = new Borrower();
        borrower.setBorrowerNo(999);
        borrower.setLastName("kostas");
        borrower.setFirstName("karakostas");
        
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        
        mock = EasyMock.createMock(BorrowerView.class);
        presenter = new BorrowerPresenter(mock);
        
        expectSetBorrower();
        mock.setPresenter(presenter);
        mock.open();
    }
    

    
    @Test
    public void wiring() {
        EasyMock.replay(mock);
        presenter.start();
        EasyMock.verify(mock);
    }
    
    
    @Test
    public void setBorrowerAndSave() {
        expectSetBorrower();
        expectGetBorrower(999, "kostas","karakostas");
        
        mock.close();
        EasyMock.replay(mock);
        
        EntityManager em = JPAUtil.createEntityManager();
        
        
        presenter.setBorrower(borrower);        
        presenter.setEntityManager(em);
        
        presenter.start();
        presenter.save();
        
        int allBorrowers = countBorrowers();
        Assert.assertEquals(3, allBorrowers);
        
        EasyMock.verify(mock);        
    }

    
    @Test
    public void setBorrowerAndCancel() {
        expectSetBorrower();
        
        mock.close();
        EasyMock.replay(mock);
        
        presenter.setBorrower(borrower);  
        presenter.start();
        presenter.cancel();
        
        int allBorrowers = countBorrowers();
        Assert.assertEquals(2, allBorrowers);
        
        EasyMock.verify(mock);        
    }
    
    @Test
    public void changeBorrowerInfoAndSave() {
        expectSetBorrower();
        expectGetBorrower(999, "nikos", "karanikos");
        mock.close();
        
        EasyMock.replay(mock);
        EntityManager em = JPAUtil.createEntityManager();
        
        presenter.setBorrower(borrower);
        presenter.setEntityManager(em);
        presenter.start();
        presenter.save();
        
        
        Assert.assertEquals("nikos", presenter.getBorrower().getFirstName());
        Assert.assertEquals("karanikos", presenter.getBorrower().getLastName());
        
        int allBorrowers = countBorrowers();
        Assert.assertEquals(3, allBorrowers);
        
        EasyMock.verify(mock);        
        
        
    }
    
    
	private int countBorrowers() {
		EntityManager em;
		em = JPAUtil.createEntityManager();
        int allBorrowers = em.createQuery("select b from Borrower b")
        		.getResultList().size();
        
        em.close();
		return allBorrowers;
	}

    private void expectGetBorrower(int borrowerNo, String firstName, String lastName) {
    	EasyMock.expect(mock.getBorrowerNo()).andReturn(borrowerNo);
        EasyMock.expect(mock.getFirstName()).andReturn(firstName);
        EasyMock.expect(mock.getLastName()).andReturn(lastName);
    }

    private void expectSetBorrower() {
    	mock.setBorrowerNo(EasyMock.anyInt());
        mock.setFirstName((String) EasyMock.anyObject());
        mock.setLastName((String) EasyMock.anyObject());
    }
    

}
