package com.mgiandia.library.dao;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface CountryDAO
{
    /**
     * Επιστρέφει όλες τις χώρες. Η κάθε χώρα είναι σε μορφή String.
     * @return Οι χώρες
     */
    List<String> getCountries();
}
