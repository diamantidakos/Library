package com.mgiandia.library.util;

import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.domain.ItemState;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ItemStateStringTest
{
    @Test
    public void memberFunctions()
    {
        ItemState[] states = new ItemState[]{ItemState.NEW, ItemState.AVAILABLE,
                ItemState.LOANED, ItemState.LOST, ItemState.WITHDRAWN};

        String[] names = new String[]{"Καινούργιο", "Διαθέσιμο", "Δανεισμένο",
                "Χαμένο", "Αποσυρμένο"};

        ItemStateString st = new ItemStateString();
        Assert.assertArrayEquals(names, st.convert(states));
    }
}
