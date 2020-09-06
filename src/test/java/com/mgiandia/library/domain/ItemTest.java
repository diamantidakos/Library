package com.mgiandia.library.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.*;

public class ItemTest {

    LocalDate nowStub;
    
    @BeforeEach 
    public void setUpTests() {
        nowStub = LocalDate.of(2007,3,1);
        SystemDateStub.setStub(nowStub);
    }
    
    @AfterEach
    public void resetTests() {
        SystemDateStub.reset();
        
    }
    
    @Test
    public void getState() {
        Item item = new Item();
        Assertions.assertEquals(ItemState.NEW, item.getState());
    }

    @Test
    public void borrowNoBorrower() {
        Item item = new Item();
        Assertions.assertNull(item.borrow(null));
        Assertions.assertEquals(ItemState.NEW, item.getState());
    }
    
    @Test
    public void borrowNoBorrowerCategory() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assertions.assertFalse(borrower.canBorrow());
        Assertions.assertNull(item.borrow(borrower));        
    }

    
    @Test
    public void borrow() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        Assertions.assertEquals(ItemState.NEW, item.getState());
        item.available();
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);        
        Assertions.assertTrue(borrower.canBorrow());        
        Loan loan = item.borrow(borrower);
        Assertions.assertNotNull(loan);        
        LocalDate now = SystemDate.now();
        Assertions.assertEquals(ItemState.LOANED, item.getState());        
        Assertions.assertEquals(now, loan.getLoanDate());            
        Assertions.assertSame(borrower, loan.getBorrower());
        Assertions.assertSame(item, loan.getItem());        
    }

    
    @Test
    public void fromNewToAvailable() {
        Item item = new Item();
        Assertions.assertEquals(ItemState.NEW, item.getState());
        item.available();
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
    }
    
  
    @Test
    public void fromAvailableToLoaned() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
                
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
       
        
        Loan loan = item.borrow(borrower);
        Assertions.assertNotNull(loan);
        
        Assertions.assertEquals(ItemState.LOANED, item.getState());
        
        item.available();
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
    }
    
    @Test
    public void fromLostToAvailable() {
        Borrower borrower = new Borrower();
        Item item = new Item();
        item.available();
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
                
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(2);
        borrower.setCategory(category);
       
        
        Loan loan = item.borrow(borrower);
        Assertions.assertNotNull(loan);
        
        item.lost();
        Assertions.assertEquals(ItemState.LOST, item.getState());
        Assertions.assertThrows(LibraryException.class, ()-> {
        	   item.available();
        });
     
    }
    
    @Test
    public void fromWithdrawnToAvailable() {
    	
    		Item item = new Item(1);   
        	item.available();
            item.withdraw();
          
        
    	Assertions.assertThrows(LibraryException.class, ()-> {
    		  item.available();
    	});
    }
    
    

}
