package com.mgiandia.library.view.Publisher.AddPublisher;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditPublisherView
{
    String getName();
    String getPhone();
    String getEmail();
    Integer getCountryPosition();
    String getAddressCity();
    String getAddressStreet();
    String getAddressNumber();
    String getAddressPostalCode();
    Integer getAttachedPublisherID();

    /**
     * Θέτει το όνομα του εκδότη.
     * @param value Το όνομα του εκδότη
     */
    void setName(String value);

    /**
     * Θέτει τον αρι8μό του εκδότη.
     * @param value Ο αρι8μός του εκδότη
     */
    void setPhone(String value);

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του εκδότη.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του εκδότη.
     */
    void setEmail(String value);

    /**
     * Θέτει την θέση της χώρας του εκδότη.
     * @param value Ο αριθμός της θέσης του εκδότη
     */
    void setCountryPosition(Integer value);

    /**
     * Θέτει την πόλη του εκδότη.
     * @param value Η πόλη του εκδότη
     */
    void setAddressCity(String value);

    /**
     * Θέτει την οδό του εκδότη.
     * @param value Η οδός του εκδότη
     */
    void setAddressStreet(String value);

    /**
     * Θέτει τον αριθμό του εκδότη.
     * @param value Ο αριθμός του εκδότη
     */
    void setAddressNumber(String value);

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    void setAddressPostalCode(String value);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Θέτει από την λίστα με τα ονόματα των χωρών
     * το όνομα.
     * @param names Η λίστα με τα ονόματα των χωρών
     */
    void setCountryList(List<String> names);

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String message);

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
}
