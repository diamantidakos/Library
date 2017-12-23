package com.mgiandia.library.view.Borrower.AddEditBorrower;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditBorrowerView
{
    String getFirstName();
    String getLastName();
    Integer getCategoryPosition();
    String getPhone();
    String getEmail();
    Integer getCountryPosition();
    String getAddressCity();
    String getAddressStreet();
    String getAddressNumber();
    String getAddressPostalCode();

    Integer getAttachedBorrowerID();

    /**
     * Θέτει το πρώτο όνομα του δανειστή.
     * @param value Το πρώτο όνομα του δανειστή
     */
    void setFirstName(String value);

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param value Το επώνυμο του συγγραφέα
     */
    void setLastName(String value);

    /**
     * Θέτει την θέση της κατηγορίας.
     * @param value Ο αριθμός της κατηγορίας
     */
    void setCategoryPosition(Integer value);

    /**
     * Θέτει τον αρι8μό του δανειζόμενου.
     * @param value Ο αρι8μός του δανειζόμενου
     */
    void setPhone(String value);

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
     */
    void setEmail(String value);

    /**
     * Θέτει την θέση της χώρας του δανειζόμενου.
     * @param value Ο αριθμός της θέσης του δανειζόμενου
     */
    void setCountryPosition(Integer value);

    /**
     * Θέτει την πόλη του δανειζόμενου.
     * @param value Η πόλη του δανειζόμενου
     */
    void setAddressCity(String value);

    /**
     * Θέτει την οδό του δανειζόμενου.
     * @param value Η οδός του δανειζόμενου
     */
    void setAddressStreet(String value);

    /**
     * Θέτει τον αριθμό του δανειζόμενου.
     * @param value Ο αριθμός του δανειζόμενου
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
     * Θέτει από την λίστα με τα ονόματα των κατηγοριών
     * την κατηγορία.
     * @param names Η λίστα με τα ονόματα των κατηγοριών
     */
    void setCategoryList(List<String> names);

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
     * Το μήνυμα που εμφανίζεται σε
     * περίπτωση error.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
}
