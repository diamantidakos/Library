package com.mgiandia.library.domain;

import org.junit.*;

import java.util.*;

import com.mgiandia.library.util.*;

public class LoanTest {
    
    Borrower borrower;
    BorrowerCategory category;
    Loan loan;
    Money dailyFine;
    
    
    @Before
    public void setUp() {
        
        Calendar now = Calendar.getInstance();
        
        now.set(Calendar.YEAR, 2007);
        now.set(Calendar.MONTH, 0);
        now.set(Calendar.DAY_OF_MONTH, 25);
        
        SystemDateStub.setStub(new SimpleCalendar(now));

        
        borrower = new Borrower();
        category = new BorrowerCategory();
        loan = new Loan();   
        dailyFine = Money.euros(5);
        
    }
    
    @After
    public void tearDown() {
        SystemDateStub.reset();
    }
    
    @Test
    public void testDueDate() {

        Assert.assertNull(loan.getDue());
        loan.setBorrower(borrower);

        Assert.assertEquals(SystemDate.now(), loan.getDue());
                
        category.setMaxLendingDays(10);
        borrower.setCategory(category);
        Assert.assertFalse(SystemDate.now().equals(loan.getDue()));                        
        
        
        Calendar due = SystemDate.now().getJavaCalendar(); 
        due.setTimeInMillis(loan.getLoanDate().getJavaCalendar().getTimeInMillis());
        due.add(Calendar.DAY_OF_MONTH, 10);        

        Assert.assertEquals(new SimpleCalendar(due),loan.getDue());
    }

    @Test
    public void testIsPending() {
        Assert.assertTrue(loan.isPending());
        loan.setReturnDate(SystemDate.now());
        Assert.assertFalse(loan.isPending());
        loan.setReturnDate(null);
        Assert.assertTrue(loan.isPending());
    }

    @Test
    public void testReturnItem() {               
        Item item = new Item();
        loan.setItem(item);
        loan.returnItem();
        Assert.assertEquals(SystemDate.now(), loan.getReturnDate());
    }

    @Test
    public void overdueWhenNoBorrowerDate() {
        Assert.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assert.assertTrue(loan.getOverdue() == 0 );        
    }
    
    @Test 
    public void overdueWhenSameWithLoanDate() {
        Assert.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assert.assertTrue(loan.getOverdue() == 0 );        
        loan.setBorrower(borrower);
        Assert.assertTrue(loan.getOverdue() == 0 );
    }
    
    @Test
    public void ovedueWhenNoMaxLedningDays() {

        Assert.assertTrue(loan.getOverdue() == 0 );
        loan.setReturnDate(SystemDate.now());        
        Assert.assertTrue(loan.getOverdue() == 0 );

        loan.setBorrower(borrower);
        Assert.assertTrue(loan.getOverdue() == 0 );

        category.setMaxLendingDays(0);
        borrower.setCategory(category);
        Assert.assertTrue(loan.getOverdue() == 0 );
        
    }
    
    @Test
    public void testGetOverdue() {
        loan.setReturnDate(SystemDate.now());        
        loan.setBorrower(borrower);
        category.setMaxLendingDays(0);
        borrower.setCategory(category);
        category.setMaxLendingDays(10);
        
        Calendar returnDate = Calendar.getInstance();
                
        returnDate.setTimeInMillis(loan.getLoanDate().getJavaCalendar().getTimeInMillis());
        
        returnDate.add(Calendar.DAY_OF_MONTH, 11);
        loan.setReturnDate(new SimpleCalendar(returnDate));
        

        
        Assert.assertEquals(1,loan.getOverdue());
        returnDate.add(Calendar.DAY_OF_MONTH, 37);
        loan.setReturnDate(new SimpleCalendar(returnDate));
        
        Assert.assertEquals(38, loan.getOverdue() );
        returnDate.add(Calendar.DAY_OF_MONTH, 350);
        loan.setReturnDate(new SimpleCalendar(returnDate));
        Assert.assertTrue(loan.getOverdue() == 388 );
        category.setMaxLendingDays(500);
        Assert.assertTrue(loan.getOverdue() == 0 );
    }

        
    
    @Test
    public void testNoFineWithUniformFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.domain.fines.UniformFineStrategy");
        testNoFines();
    }
    

    @Test
    public void testNoFineWithRelaxedFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.domain.fines.RelaxedFineStrategy");
        testNoFines();
    }
    
    @Test
    public void testNoFineWithStrictFineStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.domain.fines.StrictFineStrategy");
        testNoFines();
    }
    
    
    
    public void testNoFines() {
        
        Assert.assertEquals(Money.euros(0), loan.getFine());
        category.setMaxLendingDays(0);
        borrower.setCategory(category);        
        category.setMaxLendingDays(10);
        loan.setBorrower(borrower);
        Assert.assertEquals(Money.euros(0), loan.getFine());        
        category.setDailyFine(dailyFine);
        Assert.assertEquals(Money.euros(0), loan.getFine());
        loan.setLoanDate(new SimpleCalendar(Calendar.getInstance()));
        Assert.assertEquals(Money.euros(0), loan.getFine());
    }
    

    
}
