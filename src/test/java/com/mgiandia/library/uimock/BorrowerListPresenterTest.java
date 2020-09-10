package com.mgiandia.library.uimock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.ViewRegistry;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;
import com.mgiandia.library.ui.borrower.BorrowerListView;
import com.mgiandia.library.ui.borrower.BorrowerView;

public class BorrowerListPresenterTest {
    private Initializer dataHelper;
    private BorrowerListPresenter presenter;
    private BorrowerListView borrowerListViewMock;
    private BorrowerView borrowerViewMock;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        
        borrowerListViewMock = mock(BorrowerListView.class);
        borrowerViewMock = mock(BorrowerView.class);
        
        ViewRegistry.setBorrowerView(borrowerViewMock);
        
        presenter = new BorrowerListPresenter(borrowerListViewMock);
        
    }
    
    @AfterEach
    public void tearDown() {
        ViewRegistry.reset();
        validateMockitoUsage();
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void wiring() {
        presenter.start();
        Assertions.assertEquals(2, presenter.getBorrowers().size());
        verify(borrowerListViewMock).open();
        verify(borrowerListViewMock).setBorrowers(any(List.class));
        
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void editSelected() {
 
    	Borrower selectedBorrower = new Borrower();
    	selectedBorrower.setBorrowerNo(999);
    	selectedBorrower.setLastName("karakostas");
    	selectedBorrower.setFirstName("kostas");
    	
        borrowerListViewMock.setBorrowers(any(List.class));
        
        when(borrowerListViewMock.getSelectedBorrower()).thenReturn(selectedBorrower);
        
        borrowerViewMock.open();
        
        presenter.start();
        presenter.editSelected();
        
        verify(borrowerListViewMock).setPresenter(presenter);
        verify(borrowerViewMock, atLeastOnce()).setBorrowerNo(selectedBorrower.getBorrowerNo());
        verify(borrowerViewMock, atLeastOnce()).setFirstName(selectedBorrower.getFirstName());
        verify(borrowerViewMock, atLeastOnce()).setLastName(selectedBorrower.getLastName());
        
        
    }
    
    
    @SuppressWarnings("unchecked")
	@Test
    public void add() {
    	
        borrowerListViewMock.setBorrowers(any(List.class));
        
        presenter.start();
        presenter.addBorrower();
        verify(borrowerListViewMock).setBorrowers(any(List.class));
        verify(borrowerViewMock,atLeastOnce()).setBorrowerNo(anyInt());
        verify(borrowerViewMock, atLeastOnce()).setFirstName(null);
        verify(borrowerViewMock, atLeastOnce()).setLastName(null);
//        
    }
    
    @SuppressWarnings("unchecked")
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
         verify(borrowerListViewMock, atLeastOnce()).setBorrowers(any(List.class));
         
    }
}
