package com.mgiandia.library.domain;

import org.junit.*;

import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.util.*;

public class ItemTest {

    SimpleCalendar nowStub;
    private LoanDAO loanDao;
    
    @Before 
    public void setUpTests() {
        nowStub = new SimpleCalendar(2007,3,1);
        loanDao = new LoanDAOMemory();
        SystemDateStub.setStub(nowStub);
    }
    
    @After
    public void resetTests() {
        SystemDateStub.reset();
        
    }
    
    @Test
    public void getState() {
        Item item = new Item();
        Assert.assertEquals(ItemState.NEW, item.getState());
    }

    @Test
    public void borrowNoBorrower() {
        Item item = new Item();
        Assert.assertNull(item.borrow(null, loanDao.nextId()));
        Assert.assertEquals(ItemState.NEW, item.getState());
    }
    
    @Test
    public void borrowNoBorrowerCategory() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assert.assertFalse(borrower.canBorrow());
        Assert.assertNull(item.borrow(borrower, loanDao.nextId()));
    }

    
    @Test
    public void borrow() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        Assert.assertEquals(ItemState.NEW, item.getState());
        item.available();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);        
        Assert.assertTrue(borrower.canBorrow());        
        Loan loan = item.borrow(borrower, loanDao.nextId());
        Assert.assertNotNull(loan);        
        SimpleCalendar now = SystemDate.now();
        Assert.assertEquals(ItemState.LOANED, item.getState());        
        Assert.assertEquals(now, loan.getLoanDate());            
        Assert.assertSame(borrower, loan.getBorrower());
        Assert.assertSame(item, loan.getItem());
        Assert.assertEquals("0", new Item().toString());
    }

    @Test
    public void fromNewToAvailable() {
        Item item = new Item();
        Assert.assertEquals(ItemState.NEW, item.getState());
        item.available();
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
    }

    @Test
    public void fromAvailableToLoaned() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
                
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
       
        
        Loan loan = item.borrow(borrower, loanDao.nextId());
        Assert.assertNotNull(loan);
        
        Assert.assertEquals(ItemState.LOANED, item.getState());
        
        item.available();
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
    }
    
    @Test(expected=LibraryException.class)
    public void fromLostToAvailable() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
                
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
       
        
        Loan loan = item.borrow(borrower, loanDao.nextId());
        Assert.assertNotNull(loan);
        
        item.lost();
        Assert.assertEquals(ItemState.LOST, item.getState());
        item.available();
    }
    
    @Test(expected=LibraryException.class)
    public void fromWithdrawnToAvailable() {
        Item item = new Item(1);   
    	item.available();
        item.withdraw();
        Assert.assertEquals(ItemState.WITHDRAWN, item.getState());
        item.available();
    }

    @Test
    public void itemStateNotAvailable() {
        Item item = new Item(1);
        Borrower borrower = new Borrower();
        BorrowerCategory borrowerCategory = new BorrowerCategory();
        borrowerCategory.setMaxLendingItems(1);
        borrower.setCategory(borrowerCategory);
        item.borrow(borrower, loanDao.nextId());
    }

    @Test(expected=LibraryException.class)
    public void withdrawNotAvailable() {
        Item item = new Item(1);
        item.withdraw();
    }

    @Test(expected=LibraryException.class)
    public void lostNotLoaned() {
        Item item = new Item(1);
        item.lost();
    }
}
