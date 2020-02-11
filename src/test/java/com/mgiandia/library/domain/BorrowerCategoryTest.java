package com.mgiandia.library.domain;

import org.junit.*;


import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.util.SimpleCalendar;

public class BorrowerCategoryTest {

    BorrowerCategory category;
    
    @Before
    public void setUp() {
        category = new BorrowerCategory();
    }

    

    
    @Test
    public void dueWhenLoanDateisNull() {        
        Assert.assertNull(category.getLoanDue(null));        
    }
    
    @Test
    public void dueWhenLendingDaysAre0() {
        category.setMaxLendingDays(0);
        SimpleCalendar loanDate = get1stMarchOf2007();
        Assert.assertEquals(loanDate, category.getLoanDue(loanDate));
    }
    
    
    @Test
    public void dueWhenLendingDaysGreaterThan0() {
        category.setMaxLendingDays(4);
        SimpleCalendar loanDate = get1stMarchOf2007();
        SimpleCalendar expectedDue = get5thMarchOf2007();  
        Assert.assertEquals(expectedDue, category.getLoanDue(loanDate));
    }
    

    @Test
    public void canBorrow() {
        category.setMaxLendingItems(10);
        Assert.assertTrue(category.canBorrow(9));
        Assert.assertFalse(category.canBorrow(10));
        Assert.assertFalse(category.canBorrow(11));
    }

    
    private SimpleCalendar get1stMarchOf2007() {
        return new SimpleCalendar(2007,3,1);
    }
    
    
    private SimpleCalendar get5thMarchOf2007() {        
        return new SimpleCalendar(2007, 3, 5);
    }
    
}
