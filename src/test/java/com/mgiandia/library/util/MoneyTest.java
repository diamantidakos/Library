package com.mgiandia.library.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class MoneyTest {

    private String euroCurrency = "EUR";
    
    
    @Test
    public void equalsAndHashCode() {
        BasicEqualTester<Money> equalsTester = new BasicEqualTester<Money>();
        
        equalsTester.setObjectUnderTest(new Money(null,null));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.bothObjectsHaveNoState(new Money(null,null));
        
        equalsTester.setObjectUnderTest(Money.euros(5));        
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new Money(null,null));
        equalsTester.objectsHaveDifferentState(Money.euros(10));
        // TODO THIS FAILS equalsTester.objectsHaveDifferentState(Money.dollars(5));
        
        
        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(Money.euros(5));
    }
    
    
    
    
    @Test
    public void plusSameCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.euros(4);
        Money c = a.plus(b);
        Assertions.assertEquals(new BigDecimal(14), c.getAmount());
        Assertions.assertEquals(euroCurrency, c.getCurrency());
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(new BigDecimal(4),b.getAmount());
    }
    
    
    @Test
    public void plusDifferentCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.dollars(5);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> a.plus(b) );
    }
    
    @Test
    public void minusSameCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.euros(4);
        Money c = a.minus(b);
        Assertions.assertEquals(new BigDecimal(6), c.getAmount());
        Assertions.assertEquals("EUR", c.getCurrency());
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(new BigDecimal(4),b.getAmount());    
    }
    
    @Test
    public void minusDifferentCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.dollars(5);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> a.minus(b));
    }
    

    @Test
    public void multiply() {
        Money a = Money.euros(10);
        Money b = a.times(5);
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(euroCurrency, a.getCurrency());
        
        Assertions.assertEquals(new BigDecimal(50), b.getAmount());
        Assertions.assertEquals(euroCurrency, b.getCurrency());
        
        b = a.times(new BigDecimal(5));
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(euroCurrency, a.getCurrency());
        
        Assertions.assertEquals(new BigDecimal(50), b.getAmount());
        Assertions.assertEquals(euroCurrency, b.getCurrency());        
    }
    
    @Test
    public void divide() {
        Money a = Money.euros(10);
        Money b = a.divide(5);
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(euroCurrency, a.getCurrency());
        
        Assertions.assertEquals(new BigDecimal(2), b.getAmount());
        Assertions.assertEquals(euroCurrency, b.getCurrency());
        
        
        b = a.divide(new BigDecimal(5));
        Assertions.assertEquals(new BigDecimal(10), a.getAmount());
        Assertions.assertEquals(euroCurrency, a.getCurrency());
        
        Assertions.assertEquals(new BigDecimal(2), b.getAmount());
        Assertions.assertEquals(euroCurrency, b.getCurrency());
    }
    
    
    
}
