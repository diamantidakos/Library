package com.mgiandia.library.dao;

import org.junit.*;

public class DAOFactoryTest {

    @Test
    public void testMemoryFactoryInitialization() {
        System.setProperty("daofactory", "com.mgiandia.library.memorydao.MemoryDAOFactory");
        DAOFactory factory = DAOFactory.getFactory();
        Assert.assertNotNull(factory);
        
    }
    
}
