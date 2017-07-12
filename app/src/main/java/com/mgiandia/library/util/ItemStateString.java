package com.mgiandia.library.util;

import com.mgiandia.library.domain.ItemState;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ItemStateString
{
    /**
     * Επιστρέφει την κατάσταση των βιβλίων
     * σε μορφή string array.
     * @param state Η κατάσταση του βιβλίου
     * @return Η κατάσταση του βιβλίου ως string array
     */
    public static String[] convert(ItemState[] state)
    {
        String[] values = new String[state.length];

        for(int i = 0; i < state.length; i++)
            values[i] = convert(state[i]);

        return values;
    }

    /**
     * Επιστρέφει την κατάσταση του βιβλίου
     * σε μορφή string.
     * @param state Η κατάσταση του βιβλίου
     * @return Η κατάσταση του βιβλίου ως string
     */
    public static String convert(ItemState state)
    {
        if(state == ItemState.NEW)
            return "Καινούργιο";
        else if(state == ItemState.AVAILABLE)
            return "Διαθέσιμο";
        else if(state == ItemState.LOANED)
            return "Δανεισμένο";
        else if(state == ItemState.LOST)
            return "Χαμένο";
        else// if(state == ItemState.WITHDRAWN)
            return "Αποσυρμένο";
    }
}
