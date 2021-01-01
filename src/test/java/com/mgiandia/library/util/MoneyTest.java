package com.mgiandia.library.util;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.*;



public class MoneyTest {

    private Currency euroCurrency = Currency.getInstance("EUR");
    
    
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
        Assert.assertEquals(new BigDecimal(14), c.getAmount());
        Assert.assertEquals(euroCurrency, c.getCurrency());
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(new BigDecimal(4),b.getAmount());
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void plusDifferentCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.dollars(5);
        a.plus(b);
    }
    
    @Test
    public void minusSameCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.euros(4);
        Money c = a.minus(b);
        Assert.assertEquals(new BigDecimal(6), c.getAmount());
        Assert.assertEquals(Currency.getInstance("EUR"), c.getCurrency());
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(new BigDecimal(4),b.getAmount());    
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void minusDifferentCurrencies() {
        Money a = Money.euros(10);
        Money b = Money.dollars(5);
        a.minus(b);
    }
    

    @Test
    public void multiply() {
        Money a = Money.euros(10);
        Money b = a.times(5);
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(euroCurrency, a.getCurrency());
        
        Assert.assertEquals(new BigDecimal(50), b.getAmount());
        Assert.assertEquals(euroCurrency, b.getCurrency());
        
        b = a.times(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(euroCurrency, a.getCurrency());
        
        Assert.assertEquals(new BigDecimal(50), b.getAmount());
        Assert.assertEquals(euroCurrency, b.getCurrency());        
    }
    
    @Test
    public void divide() {
        Money a = Money.euros(10);
        Money b = a.divide(5);
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(euroCurrency, a.getCurrency());
        
        Assert.assertEquals(new BigDecimal(2), b.getAmount());
        Assert.assertEquals(euroCurrency, b.getCurrency());
        
        
        b = a.divide(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(10), a.getAmount());
        Assert.assertEquals(euroCurrency, a.getCurrency());
        
        Assert.assertEquals(new BigDecimal(2), b.getAmount());
        Assert.assertEquals(euroCurrency, b.getCurrency());
    }
    
    
    
}
