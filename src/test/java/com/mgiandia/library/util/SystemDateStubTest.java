package com.mgiandia.library.util;

import java.time.LocalDate;

import org.junit.*;

public class SystemDateStubTest {
    int YEAR_STUB = 1971;
    int MONTH_STUB = 11;
    int DATE_STUB = 30;
    
    LocalDate stub;
    
    @Before
    public void setUp() {
        SystemDateStub.reset();
        stub = LocalDate.of(YEAR_STUB, MONTH_STUB, DATE_STUB);
    }
    
    @After
    public void reset() {
        SystemDateStub.reset();
    }
    
    @Test
    public void testWithoutStub() {                            
        LocalDate realNow = LocalDate.now();
        Assert.assertEquals(SystemDate.now(), realNow);        
    }
    
    
    @Test
    public void testWithStub() {
        SystemDateStub.setStub(stub);                        
        Assert.assertEquals(stub, SystemDate.now());
        
    }
    
    
    @Test
    public void switchStubUsage() {
        Assert.assertEquals(LocalDate.now(), SystemDate.now());        
        SystemDateStub.setStub(stub);
        Assert.assertEquals(stub, SystemDate.now());        
        SystemDateStub.reset();
        Assert.assertEquals(LocalDate.now(), SystemDate.now());        
    }
    
}
