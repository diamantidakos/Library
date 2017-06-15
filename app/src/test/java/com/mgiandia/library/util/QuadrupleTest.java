package com.mgiandia.library.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class QuadrupleTest
{
    @Test
    public void memberFunctions()
    {
        Quadruple q = new Quadruple(1, "A", "B", "C");
        Assert.assertEquals(1, q.getUID());
        Assert.assertEquals("A", q.getFirst());
        Assert.assertEquals("B", q.getSecond());
        Assert.assertEquals("C", q.getThird());
    }
}
