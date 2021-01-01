package com.mgiandia.library.fines;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.fines.FineStrategy;
import com.mgiandia.library.fines.StrictFineStrategy;
import com.mgiandia.library.util.Money;

public class StrictFineStrategyTest {

    private LocalDate dueDate;
    private FineStrategy strategy;
    private Money dailyFine;
    
    @BeforeEach
    public void setUp() {
        dueDate = FineTestCalendar.get1stMarchOf2007();
        strategy = new StrictFineStrategy();
        dailyFine = Money.euros(5);
    }
    
    
    @Test
    public void noDueDate() {
        Money fine = strategy.calculateFine(null, FineTestCalendar.get1stMarchOf2007(), dailyFine);
        Assertions.assertEquals(Money.euros(0),fine);        
        
    }
    
    @Test
    public void noReturnDate() {
        Money fine = strategy.calculateFine(dueDate, null, dailyFine);
        Assertions.assertEquals(Money.euros(0),fine);        
        
    }
    
    @Test
    public void returnSameDateAsDue() {
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get1stMarchOf2007(), dailyFine);
        Assertions.assertEquals(Money.euros(0),fine);        
    }
    
    
    @Test
    public void returnPreviousDate() { 
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get28thFebruaryOf2007(), dailyFine);
        Assertions.assertEquals(Money.euros(0),fine);
    }
    
    @Test
    public void returnNextDate() {
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get2ndMarchOf2007(), dailyFine);    
        Assertions.assertEquals(dailyFine.times(3), fine);
    }
    
    @Test
    public void returnEndOfTheSameWeek() {
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get4thMarchOf2007(), dailyFine);    
        Assertions.assertEquals(dailyFine.times(3), fine);
        
    }
    
    @Test
    public void returnFirstDayOfTheNextWeek() {
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get5thMarchOf2007(), dailyFine);    
        Assertions.assertEquals(dailyFine.times(10), fine);        
    }
    
    
    @Test
    public void returnLastDayOfTheNextWeek() {
        Money fine = strategy.calculateFine(dueDate, FineTestCalendar.get11thMarchOf2007(), dailyFine);    
        Assertions.assertEquals(dailyFine.times(10), fine);                
    }
}
