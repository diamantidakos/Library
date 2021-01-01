package com.mgiandia.library.fines;

import org.junit.*;

import com.mgiandia.library.fines.FineStrategy;
import com.mgiandia.library.fines.FineStrategyFactory;
import com.mgiandia.library.fines.RelaxedFineStrategy;
import com.mgiandia.library.fines.StrictFineStrategy;
import com.mgiandia.library.fines.UniformFineStrategy;


/**
 * Ελέγχει την αρχικοποίηση του εργοστασίου της στρατηγικής προστίμου
 * @author Νίκος Διαμαντίδης
 *
 */

public class FineStrategyFactoryTest {
    
    FineStrategy strategy;
    
    @Test
    public void testGetUniformStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.UniformFineStrategy");
        getStrategy();
        Assert.assertTrue(strategy instanceof UniformFineStrategy );
        
    }

    @Test
    public void testGetStrictStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.StrictFineStrategy");
        getStrategy();
        Assert.assertTrue(strategy instanceof StrictFineStrategy );
        
    }
    
    
    @Test
    public void testGetRelaxedStrategy() {
        System.setProperty("finestrategy", "com.mgiandia.library.fines.RelaxedFineStrategy");
        getStrategy();
        Assert.assertTrue(strategy instanceof RelaxedFineStrategy );
        
    }
    
    public void getStrategy() { 
        strategy = FineStrategyFactory.getStrategy();
        Assert.assertNotNull(strategy);        
    }
    

}
