package com.mgiandia.library.view.Loans.AddLoan;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddLoansView
{
    /**
     * Επιστρέφει το id tου επιλεγόμενου βιβλίου.
     * @return Το id tου επιλεγόμενου βιβλίου
     */
    int getSelectedBookId();

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    int getAttachedBorrowerID();

    /**
     * Θέτει το id του δανιζόμενου για
     * το επιλεγόμενο βιβλίο.
     * @param value Το id του δανιζόμενου
     */
    void setBorrowerId(String value);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Το μήνυμα πoυ εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyAddLoanAndFinishActivity(String message);

    /**
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert
     * με τίτλο title και περιεχόμενο
     * message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showAlert(String title, String message);

    /**
     * Θέτει την λίστα με τα ονόματα που
     * θα εμφανίζονται.
     * @param names Η λίστα με τα ονόματα
     */
    void setBookList(List<String> names);
}
