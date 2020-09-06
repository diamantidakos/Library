package com.mgiandia.library.util;


import org.junit.jupiter.api.Assertions;

public class BasicEqualTester<T> {

    private T objectUnderTest;
    
    
    public void otherObjectIsNull() {
    	Assertions.assertFalse(getObjectUnderTest().equals(null));
        
    }
    
    public void bothObjectsHaveNoState(Object other) {
    	Assertions.assertEquals(objectUnderTest, other);
    	Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    public void otherObjectsHasNoState(Object other) {
    	Assertions.assertFalse(objectUnderTest.equals(other) );
    	Assertions.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }
    
    
    public void otherObjectIsOfDifferentType(Object other) {
    	Assertions.assertFalse(objectUnderTest.equals(other));
    }
    
    public void sameReferences(Object other) {
    	Assertions.assertEquals(objectUnderTest, other);
    	Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    
    public void bothObjectsHaveSameState(Object other) {
    	Assertions.assertEquals(objectUnderTest, other);
    	Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    public void objectsHaveDifferentState(Object other) {
    	Assertions.assertFalse(objectUnderTest.equals(other));
    	Assertions.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }

    
    public void setObjectUnderTest(T objectUnderTest) {
        this.objectUnderTest = objectUnderTest;
    }

    public T getObjectUnderTest() {
        return objectUnderTest;
    }
    
    
}
