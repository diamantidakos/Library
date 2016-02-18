package com.mgiandia.library.util;

import org.junit.*;


public class BasicEqualTester<T> {

    private T objectUnderTest;
    
    
    public void otherObjectIsNull() {
        Assert.assertFalse(getObjectUnderTest().equals(null));
        
    }
    
    public void bothObjectsHaveNoState(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    public void otherObjectsHasNoState(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other) );
        Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }
    
    
    public void otherObjectIsOfDifferentType(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other));
    }
    
    public void sameReferences(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    
    public void bothObjectsHaveSameState(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }
    
    public void objectsHaveDifferentState(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other));
        Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }

    
    public void setObjectUnderTest(T objectUnderTest) {
        this.objectUnderTest = objectUnderTest;
    }

    public T getObjectUnderTest() {
        return objectUnderTest;
    }
    
    
}
