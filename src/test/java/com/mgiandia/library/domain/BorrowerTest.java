package com.mgiandia.library.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.mgiandia.library.contacts.Address;


public class BorrowerTest {

    
    @Test
    public void testAddress() {
        Address address = new Address();
        address.setCity("Athens");
        
        Borrower borrower = new Borrower();
        borrower.setAddress(address);
        
        Assertions.assertNotSame(address, borrower.getAddress());
        Assertions.assertEquals(address, borrower.getAddress());
        
        address.setCity("Patra");
        
        Assertions.assertFalse(address.equals(borrower.getAddress()) );
        
        Address newAddress = borrower.getAddress();
        Assertions.assertNotSame(newAddress, borrower.getAddress());
        Assertions.assertTrue(newAddress.equals(borrower.getAddress()) );
        
        Assertions.assertTrue(newAddress.getCity().equals(borrower.getAddress().getCity()) );
        newAddress.setCity("Patra");
        
        Assertions.assertFalse(newAddress.getCity().equals(borrower.getAddress().getCity()) );
        
        
    }
    

    @Test
    public void setLoanToBorrower() {
        Borrower borrower = new Borrower();
        
        Assertions.assertTrue(borrower.getLoans().size() == 0 );
        
        Loan loan = new Loan();
        
        loan.setBorrower(borrower);
        
        Assertions.assertTrue(borrower.getLoans().size() == 1);
        Assertions.assertTrue(borrower.getLoans().contains(loan));
        
    }

    @Test
    public void setLoanToNewBorrower() {
        Borrower borrower = new Borrower();        
        Loan loan = new Loan();
        loan.setBorrower(borrower);
        Assertions.assertTrue(borrower.getLoans().contains(loan));
        Borrower newBorrower = new Borrower();
        loan.setBorrower(newBorrower);
        Assertions.assertTrue(borrower.getLoans().size() == 0);
                
        Assertions.assertTrue(newBorrower.getLoans().size() == 1);
        Assertions.assertTrue(newBorrower.getLoans().contains(loan));
    }
    
    @Test
    public void setLoanToNull() {
        Borrower borrower = new Borrower();        
        Loan loan = new Loan();
        loan.setBorrower(borrower);
        Assertions.assertTrue(borrower.getLoans().contains(loan));
        loan.setBorrower(null);
        Assertions.assertEquals(0, borrower.getLoans().size());
    }
    

    @Test
    public void canBorrowWhenBorrowerCategoryIsNull() {
        Borrower borrower = new Borrower();
        Assertions.assertFalse(borrower.canBorrow());        
    }

    @Test
    public void canBorrowWhenBorrowerCategoryIsSet() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(1);
        borrower.setCategory(category);
        Assertions.assertTrue(borrower.canBorrow());        
    }

    @Test
    public void canBorrowAfterALoan() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
        Loan firstLoan = new Loan();
        firstLoan.setBorrower(borrower);
        Assertions.assertTrue(borrower.canBorrow());            
    }
    
    
    @Test
    public void canBorrowExceeding() {
        Borrower borrower = new Borrower();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
        Loan firstLoan = new Loan();
        firstLoan.setBorrower(borrower);
        Assertions.assertTrue(borrower.canBorrow());            
        Loan secondLoan = new Loan();
        secondLoan.setBorrower(borrower);
        Assertions.assertFalse(borrower.canBorrow());
        secondLoan.setBorrower(null);
        Assertions.assertTrue(borrower.canBorrow());
        secondLoan.setBorrower(borrower);
        Assertions.assertFalse(borrower.canBorrow());
        category.setMaxLendingItems(3);
        Assertions.assertTrue(borrower.canBorrow());
    }
    
    
    @Test
    public void testGetLoanDue() {
    
        Borrower borrower = new Borrower();
        
        Assertions.assertNull(borrower.getLoanDue((LocalDate) null)); 
        LocalDate loanDate = LocalDate.now();
        Assertions.assertEquals(loanDate, borrower.getLoanDue(loanDate));
                
        //TODO Redudancy with BorrowerCategoryTest
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingDays(0);
        borrower.setCategory(category);
        
        
        Assertions.assertEquals(loanDate, borrower.getLoanDue(loanDate));
        category.setMaxLendingDays(10);
        
        LocalDate due = borrower.getLoanDue(loanDate);
        Assertions.assertFalse(loanDate.equals(due));
        
        LocalDate adue = loanDate.plusDays(10);
        
        Assertions.assertEquals(adue, borrower.getLoanDue(loanDate));
//        
//        aloanDate.set(Calendar.MONTH, Calendar.FEBRUARY);
//        aloanDate.set(Calendar.DAY_OF_MONTH, 20);
//        aloanDate.set(Calendar.YEAR, 2007);
//        
//        loanDate = new SimpleCalendar(aloanDate);
//        due = borrower.getLoanDue(loanDate);
//        
//        Assertions.assertEquals(Calendar.MARCH, due.getMonth()-1);
//        Assertions.assertEquals(2,due.getDayOfMonth() );
//        
//        category.setMaxLendingDays(365);
//        
//        due = borrower.getLoanDue(loanDate);
//        Assertions.assertEquals(2008, due.getYear());
//        Assertions.assertEquals(Calendar.FEBRUARY, due.getMonth()-1);
//        Assertions.assertEquals(20, due.getDayOfMonth());
        
    }
    

}
