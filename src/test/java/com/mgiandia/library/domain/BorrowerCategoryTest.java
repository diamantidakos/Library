package com.mgiandia.library.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.mgiandia.library.domain.BorrowerCategory;

public class BorrowerCategoryTest {

    BorrowerCategory category;
    
    @BeforeEach
    public void setUp() {
        category = new BorrowerCategory();
    }

    

    
    @Test
    public void dueWhenLoanDateisNull() {        
    	Assertions.assertNull(category.getLoanDue(null));        
    }
    
    @Test
    public void dueWhenLendingDaysAre0() {
        category.setMaxLendingDays(0);
        LocalDate loanDate = get1stMarchOf2007();
        Assertions.assertEquals(loanDate, category.getLoanDue(loanDate));
    }
    
    
    @Test
    public void dueWhenLendingDaysGreaterThan0() {
        category.setMaxLendingDays(4);
        LocalDate loanDate = get1stMarchOf2007();
        LocalDate expectedDue = get5thMarchOf2007();  
        Assertions.assertEquals(expectedDue, category.getLoanDue(loanDate));
    }
    

    @Test
    public void canBorrow() {
        category.setMaxLendingItems(10);
        Assertions.assertTrue(category.canBorrow(9));
        Assertions.assertFalse(category.canBorrow(10));
        Assertions.assertFalse(category.canBorrow(11));
    }

    
    private LocalDate get1stMarchOf2007() {
    	
        return LocalDate.of(2007, 3, 1);
    }
    
    
    private LocalDate get5thMarchOf2007() {        
        return LocalDate.of(2007, 3, 5);
    }
    
}
