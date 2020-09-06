package com.mgiandia.library.domain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.mgiandia.library.util.Money;


/**
 * 
 * @author ndia
 *
 */

public class FineTest {

    private Money dailyFine;
    private Borrower borrower;
    private BorrowerCategory category;
    private Loan loan;
    
    
    @BeforeEach
    public void setUp() {
        FineTestCalendar.get1stMarchOf2007();
        dailyFine = Money.euros(5);
        category = new BorrowerCategory();
        category.setDailyFine(dailyFine);
        borrower = new Borrower();
        borrower.setCategory(category);
        loan = new Loan();
        loan.setBorrower(borrower);

    }
    
    
    @Test
    public void noDueDate() {
        loan.setReturnDate(FineTestCalendar.get1stMarchOf2007());
        Money fine = loan.getFine(); 
        Assertions.assertEquals(Money.euros(0),fine);        
        
    }
    
    @Test
    public void noReturnDate() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        Money fine = loan.getFine();
        Assertions.assertEquals(Money.euros(0),fine);        
        
    }
    
    @Test
    public void returnSameDateAsDue() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get1stMarchOf2007());
        Money fine = loan.getFine();
        Assertions.assertEquals(Money.euros(0),fine);        
    }
    
    
    @Test
    public void returnPreviousDate() { 
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get28thFebruaryOf2007());
        Money fine = loan.getFine();
        Assertions.assertEquals(Money.euros(0),fine);
    }
    
    @Test
    public void returnNextDate() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get2ndMarchOf2007());
    	Money fine = loan.getFine();    
        Assertions.assertEquals(dailyFine, fine);
    }
    
    @Test
    public void returnEndOfTheSameWeek() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get4thMarchOf2007());
        Money fine = loan.getFine();    
        Assertions.assertEquals(dailyFine.times(3), fine);
        
    }
    
    @Test
    public void returnFirstDayOfTheNextWeek() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get5thMarchOf2007());
        Money fine = loan.getFine();
        Assertions.assertEquals(dailyFine.times(4), fine);        
    }
    
    
    @Test
    public void returnLastDayOfTheNextWeek() {
        loan.setLoanDate(FineTestCalendar.get1stMarchOf2007());
        loan.setReturnDate(FineTestCalendar.get11thMarchOf2007());
    	Money fine = loan.getFine();    
        Assertions.assertEquals(dailyFine.times(10), fine);                
    }
}
