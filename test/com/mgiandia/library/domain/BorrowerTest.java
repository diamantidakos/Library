package com.mgiandia.library.domain;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.util.SimpleCalendar;


public class BorrowerTest {

    
    @Test
    public void testAddress() {
        Address address = new Address();
        address.setCity("Athens");
        
        Borrower borrower = new Borrower();
        borrower.setAddress(address);
        
        Assert.assertNotSame(address, borrower.getAddress());
        Assert.assertEquals(address, borrower.getAddress());
        
        address.setCity("Patra");
        
        Assert.assertFalse(address.equals(borrower.getAddress()) );
        
        Address newAddress = borrower.getAddress();
        Assert.assertNotSame(newAddress, borrower.getAddress());
        Assert.assertTrue(newAddress.equals(borrower.getAddress()) );
        
        Assert.assertTrue(newAddress.getCity().equals(borrower.getAddress().getCity()) );
        newAddress.setCity("Patra");
        
        Assert.assertFalse(newAddress.getCity().equals(borrower.getAddress().getCity()) );
        
        
    }
    

    @Test
    public void setLoanToBorrower() {
        Borrower borrower = new Borrower();
        
        Assert.assertTrue(borrower.getLoans().size() == 0 );
        
        Loan loan = new Loan();
        
        loan.setBorrower(borrower);
        
        Assert.assertTrue(borrower.getLoans().size() == 1);
        Assert.assertTrue(borrower.getLoans().contains(loan));
        
    }

    @Test
    public void setLoanToNewBorrower() {
        Borrower borrower = new Borrower();        
        Loan loan = new Loan();
        loan.setBorrower(borrower);
        Assert.assertTrue(borrower.getLoans().contains(loan));
        Borrower newBorrower = new Borrower();
        loan.setBorrower(newBorrower);
        Assert.assertTrue(borrower.getLoans().size() == 0);
                
        Assert.assertTrue(newBorrower.getLoans().size() == 1);
        Assert.assertTrue(newBorrower.getLoans().contains(loan));
    }
    
    @Test
    public void setLoanToNull() {
        Borrower borrower = new Borrower();        
        Loan loan = new Loan();
        loan.setBorrower(borrower);
        Assert.assertTrue(borrower.getLoans().contains(loan));
        loan.setBorrower(null);
        Assert.assertEquals(0, borrower.getLoans().size());
    }
    

    @Test
    public void canBorrowWhenBorrowerCategoryIsNull() {
        Borrower borrower = new Borrower();
        Assert.assertFalse(borrower.canBorrow());        
    }

    @Test
    public void canBorrowWhenBorrowerCategoryIsSet() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(1);
        borrower.setCategory(category);
        Assert.assertTrue(borrower.canBorrow());        
    }

    @Test
    public void canBorrowAfterALoan() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
        Loan firstLoan = new Loan();
        firstLoan.setBorrower(borrower);
        Assert.assertTrue(borrower.canBorrow());            
    }
    
    
    @Test
    public void canBorrowExceeding() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
        Loan firstLoan = new Loan();
        firstLoan.setBorrower(borrower);
        Assert.assertTrue(borrower.canBorrow());            
        Loan secondLoan = new Loan();
        secondLoan.setBorrower(borrower);
        Assert.assertFalse(borrower.canBorrow());
        secondLoan.setBorrower(null);
        Assert.assertTrue(borrower.canBorrow());
        secondLoan.setBorrower(borrower);
        Assert.assertFalse(borrower.canBorrow());
        category.setMaxLendingItems(3);
        Assert.assertTrue(borrower.canBorrow());
    }
    
    
    @Test
    public void testGetLoanDue() {
    
        Borrower borrower = new Borrower();
        
        Assert.assertNull(borrower.getLoanDue((SimpleCalendar) null)); 
        SimpleCalendar loanDate = new SimpleCalendar(Calendar.getInstance());
        Assert.assertEquals(loanDate, borrower.getLoanDue(loanDate));
                
        //TODO Redudancy with BorrowerCategoryTest
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingDays(0);
        borrower.setCategory(category);
        
        Calendar aloanDate = Calendar.getInstance();
        aloanDate.set(Calendar.DAY_OF_MONTH, 1);
        loanDate = new SimpleCalendar(aloanDate);
        
        Assert.assertEquals(loanDate, borrower.getLoanDue(loanDate));
        category.setMaxLendingDays(10);
        
        SimpleCalendar due = borrower.getLoanDue(loanDate);
        Assert.assertFalse(loanDate.equals(due));
        
        Calendar adue = Calendar.getInstance();
        adue.setTimeInMillis(loanDate.getJavaCalendar().getTimeInMillis());
        
        adue.add(Calendar.DAY_OF_MONTH, 10);
        
        due = new SimpleCalendar(adue);
        
        Assert.assertEquals(due, borrower.getLoanDue(loanDate));
        
        aloanDate.set(Calendar.MONTH, Calendar.FEBRUARY);
        aloanDate.set(Calendar.DAY_OF_MONTH, 20);
        aloanDate.set(Calendar.YEAR, 2007);
        
        loanDate = new SimpleCalendar(aloanDate);
        due = borrower.getLoanDue(loanDate);
        
        Assert.assertEquals(Calendar.MARCH, due.getMonth()-1);
        Assert.assertEquals(2,due.getDayOfMonth() );
        
        category.setMaxLendingDays(365);
        
        due = borrower.getLoanDue(loanDate);
        Assert.assertEquals(2008, due.getYear());
        Assert.assertEquals(Calendar.FEBRUARY, due.getMonth()-1);
        Assert.assertEquals(20, due.getDayOfMonth());
        
    }
    

}
