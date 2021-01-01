package com.mgiandia.library.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;

import com.mgiandia.library.util.*;

public class LoanTest {
    
    Borrower borrower;
    BorrowerCategory category;
    Loan loan;
    Money dailyFine;
    
    
    @BeforeEach
    public void setUp() {
        
        LocalDate now = LocalDate.of(2007, 1, 25);
        SystemDateStub.setStub(now);
        borrower = new Borrower();
        category = new BorrowerCategory();
        loan = new Loan();   
        dailyFine = Money.euros(5);
        
    }
    
    @AfterEach
    public void tearDown() {
        SystemDateStub.reset();
    }
    
    @Test
    public void testDueDate() {

        Assertions.assertNull(loan.getDue());
        loan.setBorrower(borrower);

        Assertions.assertEquals(SystemDate.now(), loan.getDue());
                
        category.setMaxLendingDays(10);
        borrower.setCategory(category);
        Assertions.assertFalse(SystemDate.now().equals(loan.getDue()));                        

        LocalDate due = SystemDate.now().plusDays(10);
        
        Assertions.assertEquals(due,loan.getDue());
    }

    @Test
    public void testIsPending() {
        Assertions.assertTrue(loan.isPending());
        loan.setReturnDate(SystemDate.now());
        Assertions.assertFalse(loan.isPending());
        loan.setReturnDate(null);
        Assertions.assertTrue(loan.isPending());
    }

    @Test
    public void testReturnItem() {               
        Item item = new Item();
        loan.setItem(item);
        loan.returnItem();
        Assertions.assertEquals(SystemDate.now(), loan.getReturnDate());
    }

    @Test
    public void overdueWhenNoBorrowerDate() {
        Assertions.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assertions.assertTrue(loan.getOverdue() == 0 );        
    }
    
    @Test 
    public void overdueWhenSameWithLoanDate() {
        Assertions.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assertions.assertTrue(loan.getOverdue() == 0 );        
        loan.setBorrower(borrower);
        Assertions.assertTrue(loan.getOverdue() == 0 );
    }
    
    @Test
    public void ovedueWhenNoMaxLedningDays() {

        Assertions.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assertions.assertTrue(loan.getOverdue() == 0 );

        loan.setBorrower(borrower);
        Assertions.assertTrue(loan.getOverdue() == 0 );

        category.setMaxLendingDays(0);
        borrower.setCategory(category);
        Assertions.assertTrue(loan.getOverdue() == 0 );
        
    }
    
    @Test
    public void testGetOverdue() {
        loan.setReturnDate(SystemDate.now());        
        loan.setBorrower(borrower);
        borrower.setCategory(category);
        category.setMaxLendingDays(10);
        
        LocalDate returnDate = loan.getLoanDate().plusDays(11);
        loan.setReturnDate(returnDate);
        
        Assertions.assertEquals(1,loan.getOverdue());
        
        returnDate = returnDate.plusDays(37);
        loan.setReturnDate(returnDate);
        Assertions.assertEquals(38, loan.getOverdue() );
        
        
        returnDate = returnDate.plusDays(350);
        loan.setReturnDate(returnDate);
        Assertions.assertTrue(loan.getOverdue() == 388 );
        category.setMaxLendingDays(500);
        Assertions.assertTrue(loan.getOverdue() == 0 );
    }

        
    
    @Test
    public void testNoFineWithUniformFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.UniformFineStrategy");
        testNoFines();
    }
    

    @Test
    public void testNoFineWithRelaxedFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.RelaxedFineStrategy");
        testNoFines();
    }
    
    @Test
    public void testNoFineWithStrictFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.StrictFineStrategy");
        testNoFines();
    }
    
    
    
    public void testNoFines() {
        
        Assertions.assertEquals(Money.euros(0), loan.getFine());
        category.setMaxLendingDays(0);
        borrower.setCategory(category);        
        category.setMaxLendingDays(10);
        loan.setBorrower(borrower);
        Assertions.assertEquals(Money.euros(0), loan.getFine());        
        category.setDailyFine(dailyFine);
        Assertions.assertEquals(Money.euros(0), loan.getFine());
        loan.setLoanDate(LocalDate.now());
        Assertions.assertEquals(Money.euros(0), loan.getFine());
    }
    

    
}
