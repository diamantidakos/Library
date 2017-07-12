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
     * Θέτει το πρώτο όνομα του δανιζόμενου.
     * @param value Το πρώτο όνομα του δανιζόμενου
     */
    void setFirstName(String value);

    /**
     * Θέτει το επώνυμο του δανιζόμενου.
     * @param value Το επώνυμο του δανιζόμενου
     */
    void setLastName(String value);

    /**
     * Θέτει την κατηγορία του δανιζόμενου.
     * @param value Η κατηγορία του δανιζόμενου
     */
    void setCategory(String value);

    /**
     * Θέτει τον αρι8μό του δανιζόμενου.
     * @param value Ο αρι8μός του δανιζόμενου
     */
    void setPhone(String value);

    /**
     * Θέτει τον αριθμό ηλελτρονικό ταχυδρομίου του δανιζόμενου.
     * @param value Ο αρι8μός του ηλελτρονικό ταχυδρομίου του δανιζόμενου.
     */
    void setEmail(String value);

    /**
     * Θέτει την χώρα του δανιζόμενου.
     * @param value Η χώρα του δανιζόμενου
     */
    void setCountry(String value);

    /**
     * Θέτει την πόλη του δανιζόμενου.
     * @param value Η πόλη του δανιζόμενου
     */
    void setAddressCity(String value);

    /**
     * Θέτει την οδό του δανιζόμενου.
     * @param value Η οδός του δανιζόμενου
     */
    void setAddressStreet(String value);

    /**
     * Θέτει τον αριθμό του δανιζόμενου.
     * @param value Ο αριθμός του δανιζόμενου
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
     * με παράμετρο το id του δανιζόμενου.
     * @param borrowerID Το id του δανιζόμενου
     */
    void startEdit(int borrowerID);

    /**
     * Διαγράφη του δανιζόμενου.
     * @param title Τίτλος του εμαφανιζόμενου μηνύματος
     * @param message Το περιεχόμενο του εμαφανιζόμενου μηνύματος
     */
    void startDelete(String title, String message);

    /**
     * Κατα την διαγραφή του δανιζόμενου
     * το μήνυμα που εμφανίζεται καθώς και
     * η ολοκλήρωση της διαγραφής του.
     * @param message Το μήνυμα
     */
    void doDeleteAndFinish(String message);

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
