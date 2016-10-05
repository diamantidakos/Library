package com.mgiandia.library.contacts;

import org.junit.*;

import com.mgiandia.library.util.BasicEqualTester;

public class TelephoneNumberTest {

    @Test
    public void equalsAndHashCode() {
        BasicEqualTester<TelephoneNumber> equalsTester = new BasicEqualTester<TelephoneNumber>();
        
        equalsTester.setObjectUnderTest(new TelephoneNumber(null));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.bothObjectsHaveNoState(new TelephoneNumber(null));
        
        equalsTester.setObjectUnderTest(new TelephoneNumber("123"));        
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new TelephoneNumber(null));
        equalsTester.objectsHaveDifferentState(new TelephoneNumber("321"));
        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(new TelephoneNumber("123"));
    }
    
    
}
