package com.mgiandia.library.view.Borrower.BorrowerDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BorrowerDetailsView
{
    int getAttachedBorrowerID();


    void setID(String value);

    /**
     * Θέτει το πρώτο όνομα του δανειζόμενου.
     * @param value Το πρώτο όνομα του δανειζόμενου
     */
    void setFirstName(String value);

    /**
     * Θέτει το επώνυμο του δανειζόμενου.
     * @param value Το επώνυμο του δανειζόμενου
     */
    void setLastName(String value);

    /**
     * Θέτει την κατηγορία του δανειζόμενου.
     * @param value Η κατηγορία του δανειζόμενου
     */
    void setCategory(String value);

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
     * Θέτει την χώρα του δανειζόμενου.
     * @param value Η χώρα του δανειζόμενου
     */
    void setCountry(String value);

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
     * Ξεκινάει το activity AddEditAuthorActivity
     * με παράμετρο το id του δανειζόμενου.
     * @param borrowerID Το id του δανειζόμενου
     */
    void startEdit(int borrowerID);

    /**
     * Διαγράφει τον δανειζόμενο
     * @param title Τίτλος του εμφανιζόμενου μηνύματος
     * @param message Το περιεχόμενο του εμφανιζόμενου μηνύματος
     */
    void startDelete(String title, String message);

    /**
     * Κατά την διαγραφή του δανειζόμενου
     * το μήνυμα που εμφανίζεται καθώς και
     * η ολοκλήρωση της διαγραφής του.
     * @param message Το μήνυμα
     */
    void doDeleteAndFinish(String message);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
