package com.mgiandia.library.domain;

import org.junit.*;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.*;

public class ItemTest {

    SimpleCalendar nowStub;
    
    @Before 
    public void setUpTests() {
        nowStub = new SimpleCalendar(2007,3,1);
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
        Assert.assertNull(item.borrow(null));
        Assert.assertEquals(ItemState.NEW, item.getState());
    }
    
    @Test
    public void borrowNoBorrowerCategory() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assert.assertFalse(borrower.canBorrow());
        Assert.assertNull(item.borrow(borrower));        
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
        Loan loan = item.borrow(borrower);
        Assert.assertNotNull(loan);        
        SimpleCalendar now = SystemDate.now();
        Assert.assertEquals(ItemState.LOANED, item.getState());        
        Assert.assertEquals(now, loan.getLoanDate());            
        Assert.assertSame(borrower, loan.getBorrower());
        Assert.assertSame(item, loan.getItem());        
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
       
        
        Loan loan = item.borrow(borrower);
        Assert.assertNotNull(loan);
        
        Assert.assertEquals(ItemState.LOANED, item.getState());
        
        item.available();
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
    }
    
    @Test(expected=LibraryException.class)
    public void fromLostToAvailable() {
        Item item = new Item(1);        
        item.lost();
        item.available();
    }
    
    @Test(expected=LibraryException.class)
    public void fromWithdrawnToAvailable() {
        Item item = new Item(1);
        item.withdraw();
        item.available();
    }
    
    

}
