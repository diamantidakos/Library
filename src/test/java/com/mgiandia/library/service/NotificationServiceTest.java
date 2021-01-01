package com.mgiandia.library.service;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.contacts.EmailMessage;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.util.SystemDateStub;

public class NotificationServiceTest {
    
    
    private EmailProviderStub provider;
    
 
    
    @Before
    public void setUp() {
        provider = new EmailProviderStub();
        
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
     
    }
    
    @After
    public void tearDown() {
        SystemDateStub.reset();
    }
    
    @Test(expected=LibraryException.class)
    public void serviceWhenNotifierIsNull() {
        NotificationService service = new NotificationService();
        service.setProvider(null);
        service.notifyBorrowers();
    }
    
    
    /**
     * Πραγματοποιούμε δύο δανεισμούς. Για τον πρώτο έχει παρέλθει η
     * προθεσμία επιστροφής, ενώ για το δεύτερο όχι.
     * Περιμένουμε την αποστολή μόνο ενός μηνύματος καθυστέρησης
     * για τον πρώτο δανεισμό.
     */
    @Test
    public void sendMessageOnOverdue() {
        // Ρυθμίζουμε την ημερομηνία του συστήματος για
        // την 1η Μαρτίου 2007 και δανείζουμε ένα αντίτυπο

        setSystemDateTo1stMarch2007();            
        borrowUMLUserGuideToDiamantidis();

        // Ρυθμίζουμε την ημερομηνία του συστήματος για
        // την 1η Σεπτεμβρίου 20007 και δανειζουμε ένα αντίτυπο
        
        setSystemDateTo1stSeptember2007();
        borrowRefactoringToGiakoumakis();
        
        // ρυθμίζουμε την ημερομηνία για την 1η Νοεμβρίου
        setSystemDateTo1stNovember2007();                      
        NotificationService service = new NotificationService();        
        service.setProvider(provider);                        
        service.notifyBorrowers();
        
        BorrowerDAO borrowerDao = DAOFactory.getFactory().getBorrowerDAO();
        
        Borrower diamantidis = borrowerDao.find(Initializer.DIAMANTIDIS_ID);        
        Assert.assertEquals(1,provider.allMessages.size());
        EmailMessage message = provider.getAllEmails().get(0);
        Assert.assertEquals(diamantidis.getEmail() , message.getTo());
    }
    
    private void setSystemDateTo1stMarch2007() {        
        SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
    }
    
    
    private void setSystemDateTo1stNovember2007() {
        SystemDateStub.setStub(LocalDate.of(2007, 11, 1));
    }
    
    
    private void setSystemDateTo1stSeptember2007() {
        SystemDateStub.setStub(LocalDate.of(2007, 9, 1));
    }
    
    private void borrowUMLUserGuideToDiamantidis() {
        LoanService service = new LoanService();
        service.findBorrower(Initializer.DIAMANTIDIS_ID);
        service.borrow(Initializer.UML_USER_GUIDE_ID1);
    }
    
    
    private void borrowRefactoringToGiakoumakis() {
        LoanService service = new LoanService();
        service.findBorrower(Initializer.GIAKOUMAKIS_ID);
        service.borrow(Initializer.UML_REFACTORING_ID);
    }
    
}
