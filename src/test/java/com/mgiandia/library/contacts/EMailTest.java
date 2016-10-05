package com.mgiandia.library.contacts;

import org.junit.*;

import com.mgiandia.library.util.BasicEqualTester;



public class EMailTest {

    @Test
    public void equalsAndHashCode() {
        
        BasicEqualTester<EmailAddress> equalsTester = new BasicEqualTester<EmailAddress>();
                        
        equalsTester.setObjectUnderTest(new EmailAddress(null));        
        
        
        equalsTester.otherObjectIsOfDifferentType(new Object());            
        
        equalsTester.bothObjectsHaveNoState(new EmailAddress(null));
        
        equalsTester.setObjectUnderTest(new EmailAddress("a@b.gr"));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new EmailAddress(null));        
        equalsTester.objectsHaveDifferentState(new EmailAddress("123@123.com"));        
        equalsTester.bothObjectsHaveSameState(new EmailAddress("a@b.gr"));

        EmailAddress email2 = equalsTester.getObjectUnderTest();
        equalsTester.sameReferences(email2);        
    }

}
