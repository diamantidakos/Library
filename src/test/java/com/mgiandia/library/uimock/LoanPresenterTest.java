package com.mgiandia.library.uimock;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atLeast;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.ui.loan.LoanPresenter;
import com.mgiandia.library.ui.loan.LoanView;

public class LoanPresenterTest {
    private Initializer dataHelper;
    private LoanPresenter presenter;
    private LoanView mock;
    
    @BeforeEach
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        mock = mock(LoanView.class);
        presenter = new LoanPresenter(mock);
        
    }

    @AfterEach
    public void verifyMocking() {
    	 validateMockitoUsage();
    }
    
    @Test
    public void wirining() {  
        presenter.start();
        
        Assertions.assertFalse(presenter.isBorrowerFound());
        Assertions.assertFalse(presenter.isItemFound());
        verify(mock).setPresenter(presenter);
        verify(mock).open();
    }
    
    
    @Test
    public void cancel() {
    
        
        presenter.start();
        presenter.cancel();
        verify(mock).close();
    }
    
    
    /**
     * Αναζήτηση δανειζομένου όταν αυτός δεν υπάρχει
     */
    @Test
    public void findBorrowerWhenIdDoesNotExist() {
        when(mock.getBorrowerNo()).thenReturn(4711);      
        presenter.start();
        presenter.findBorrower();
        
        Assertions.assertFalse(presenter.isBorrowerFound());        
        verify(mock).showError(any(String.class));
    }
    
    
    @Test
    public void findBorrowerWhenIdExists() {
        when(mock.getBorrowerNo()).thenReturn(Initializer.DIAMANTIDIS_ID);      
  
        presenter.start();
        presenter.findBorrower();
        
        Assertions.assertTrue(presenter.isBorrowerFound());
        Assertions.assertEquals(presenter.getBorrower().getBorrowerNo(), Initializer.DIAMANTIDIS_ID);
        verify(mock,atLeast(2)).setLoanActionEnabled(false);
    }
    
    @Test
    public void findItemWhenIdDoesNotExist() {
        when(mock.getItemNumber()).thenReturn(4711);
        
        presenter.start();
        presenter.findItem();
        
        Assertions.assertFalse(presenter.isItemFound());
        verify(mock).showError(any(String.class));        
    }
    
    
    @Test
    public void findItemWhenIdExists() {
        when(mock.getItemNumber()).thenReturn(Initializer.UML_DISTILLED_ID1);
        
        
        presenter.start();
        presenter.findItem();
        
        Assertions.assertTrue(presenter.isItemFound());
        Assertions.assertEquals(presenter.getItem().getItemNumber(), Initializer.UML_DISTILLED_ID1);
        
        verify(mock).setBookTitle(any(String.class));
        
    }
    
    @Test
    public void performLoan() {
        when(mock.getBorrowerNo()).thenReturn(Initializer.DIAMANTIDIS_ID);
        when(mock.getItemNumber()).thenReturn(Initializer.UML_DISTILLED_ID1);
        
        presenter.start();
        presenter.findBorrower();
        presenter.findItem();
        presenter.borrowItem();
        
        Assertions.assertEquals(1, presenter.getBorrower().getLoans().size());
        
        verify(mock,atLeastOnce() ).showInfo(any(String.class));
    }
    
}
