package com.mgiandia.library.uimock;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.ui.loan.LoanPresenter;
import com.mgiandia.library.ui.loan.LoanView;

public class LoanPresenterTest {
    private Initializer dataHelper;
    private LoanPresenter presenter;
    private LoanView mock;
    
    @Before
    public void setUp() {
        dataHelper = new Initializer();
        dataHelper.prepareData();        
        mock = EasyMock.createMock(LoanView.class);
        presenter = new LoanPresenter(mock);
        mock.setPresenter(presenter);
        mock.open();
        mock.setLoanActionEnabled(false);
        
        
    }

    @Test
    public void wirining() {  
        EasyMock.replay(mock);
        presenter.start();
        
        Assert.assertFalse(presenter.isBorrowerFound());
        Assert.assertFalse(presenter.isItemFound());
        EasyMock.verify(mock);
    }
    
    
    @Test
    public void cancel() {
        mock.close();
        EasyMock.replay(mock);
        
        presenter.start();
        presenter.cancel();
        EasyMock.verify(mock);
    }
    
    
    /**
     * Αναζήτηση δανειζομένου όταν αυτός δεν υπάρχει
     */
    @Test
    public void findBorrowerWhenIdDoesNotExist() {
        EasyMock.expect(mock.getBorrowerNo()).andReturn(4711);      
        mock.setLoanActionEnabled(false);
        mock.showError((String) EasyMock.anyObject());
        mock.setBorrowerLastName("");
        mock.setBorrowerFirstName("");       
              
        EasyMock.replay(mock);
        
        presenter.start();
        presenter.findBorrower();
        
        Assert.assertFalse(presenter.isBorrowerFound());        
        EasyMock.verify(mock);
    }
    
    
    @Test
    public void findBorrowerWhenIdExists() {
        EasyMock.expect(mock.getBorrowerNo()).andReturn(Initializer.DIAMANTIDIS_ID);      
        mock.setLoanActionEnabled(false);        
        mock.setBorrowerLastName((String) EasyMock.anyObject());
        mock.setBorrowerFirstName((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);
        presenter.start();
        presenter.findBorrower();
        
        Assert.assertTrue(presenter.isBorrowerFound());
        Assert.assertEquals(presenter.getBorrower().getBorrowerNo(), Initializer.DIAMANTIDIS_ID);
        EasyMock.verify(mock);
    }
    
    @Test
    public void findItemWhenIdDoesNotExist() {
        EasyMock.expect(mock.getItemNumber()).andReturn(4711);
        mock.setLoanActionEnabled(false);
        mock.showError((String) EasyMock.anyObject());
        mock.setBookTitle("");
        
        EasyMock.replay(mock);
        presenter.start();
        presenter.findItem();
        
        Assert.assertFalse(presenter.isItemFound());
        EasyMock.verify(mock);        
    }
    
    
    @Test
    public void findItemWhenIdExists() {
        EasyMock.expect(mock.getItemNumber()).andReturn(Initializer.UML_DISTILLED_ID1);
        mock.setLoanActionEnabled(false);
        mock.setBookTitle((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);
        presenter.start();
        presenter.findItem();
        
        Assert.assertTrue(presenter.isItemFound());
        Assert.assertEquals(presenter.getItem().getItemNumber(), Initializer.UML_DISTILLED_ID1);
        
        EasyMock.verify(mock);
        
    }
    
    @Test
    public void performLoan() {
        EasyMock.expect(mock.getBorrowerNo()).andReturn(Initializer.DIAMANTIDIS_ID);
        EasyMock.expect(mock.getItemNumber()).andReturn(Initializer.UML_DISTILLED_ID1);
        mock.setBorrowerLastName((String) EasyMock.anyObject());
        mock.setBorrowerFirstName((String) EasyMock.anyObject());
        mock.setBookTitle((String) EasyMock.anyObject());
        
        mock.setLoanActionEnabled(false);
        mock.setLoanActionEnabled(true);
        
        mock.showInfo((String) EasyMock.anyObject());
        
        EasyMock.replay(mock);
        
        presenter.start();
        presenter.findBorrower();
        presenter.findItem();
        presenter.borrowItem();
        
        Assert.assertEquals(1, presenter.getBorrower().getLoans().size());
        
        EasyMock.verify(mock);
    }
    
}
