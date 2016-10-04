package com.mgiandia.library.util;

import java.util.Calendar;

import org.junit.*;



public class SimpleCalendarTest {

    @Test
    public void creation() {
        SimpleCalendar march_1_2007 = new SimpleCalendar(2007, 3 , 1);
        assert1stMarch2007(march_1_2007);
    }    
    
    
    @Test
    public void creationFormCalendar() {
        Calendar date = Calendar.getInstance();
        date.set(2007,Calendar.MARCH,1);
        SimpleCalendar march_1_2007 = new SimpleCalendar(date);
        assert1stMarch2007(march_1_2007);
    }
    
    @Test
    public void preserveDateInvirant() {
        SimpleCalendar date = new SimpleCalendar(2007, 2, 29);
        assert1stMarch2007(date);
    }
    
    
    @Test
    public void addDays() {
        SimpleCalendar date = new SimpleCalendar(2007, 2, 28);
        SimpleCalendar march_1_2007 = date.addDays(1);
        assert1stMarch2007(march_1_2007);
        Assert.assertFalse(date.equals(march_1_2007));
    }
    
    
    
    @Test
    public void beforeAndAfter() {
        SimpleCalendar date = new SimpleCalendar(2007, 3, 1);
    
        Assert.assertTrue(date.before(new SimpleCalendar(2007, 3, 2)));
        Assert.assertTrue(date.compareTo(new SimpleCalendar(2007, 3, 2)) < 0);        
        
        Assert.assertTrue(date.after(new SimpleCalendar(2007, 2, 28)));
        Assert.assertTrue(date.compareTo(new SimpleCalendar(2007, 2, 28)) > 0);
        
        Assert.assertFalse(date.after(new SimpleCalendar(2007, 3, 1)));                       
        Assert.assertFalse(date.before(new SimpleCalendar(2007, 3, 1)));
        Assert.assertEquals(0, date.compareTo(new SimpleCalendar(2007, 3, 1)));        
    }
    
    
    @Test
    public void getJavaCalendar() {
        SimpleCalendar date = new SimpleCalendar(2007, 3, 1);
        Calendar javaDate = date.getJavaCalendar();
        
        Assert.assertEquals(2007, javaDate.get(Calendar.YEAR));
        Assert.assertEquals(Calendar.MARCH, javaDate.get(Calendar.MONTH));
        Assert.assertEquals(1, javaDate.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(0, javaDate.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(0, javaDate.get(Calendar.MINUTE));
        Assert.assertEquals(0, javaDate.get(Calendar.SECOND));
        Assert.assertEquals(0, javaDate.get(Calendar.MILLISECOND));
    }
    

    @Test
    public void duration() {
        SimpleCalendar february_28_2007 = new SimpleCalendar(2007, 2, 28);
        SimpleCalendar march_1_2007 = new SimpleCalendar(2007, 3, 1);
        
        Assert.assertEquals(0, february_28_2007.durationInDays(new SimpleCalendar(2007, 2, 28)));
        Assert.assertEquals(1, february_28_2007.durationInDays(march_1_2007));
        Assert.assertEquals(-1, march_1_2007.durationInDays(february_28_2007));        
    }
    
    
    @Test
    public void equalsAndHashCode() {
        BasicEqualTester<SimpleCalendar> equalsTester = new BasicEqualTester<SimpleCalendar> ();
        equalsTester.setObjectUnderTest(new SimpleCalendar(2007, 3, 1));
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.otherObjectIsNull();
        equalsTester.objectsHaveDifferentState(new SimpleCalendar(2007, 3, 2));
        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(new SimpleCalendar(2007, 3, 1));
    }
    
    
    
    
    private void assert1stMarch2007(SimpleCalendar date) {
        Assert.assertEquals(2007, date.getYear() );
        Assert.assertEquals(3,date.getMonth());
        Assert.assertEquals(1, date.getDayOfMonth());
    }
}
