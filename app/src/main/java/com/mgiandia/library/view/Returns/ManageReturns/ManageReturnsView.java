package com.mgiandia.library.view.Returns.ManageReturns;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageReturnsView
{
    /**
     * Φορτώνει την λίστα με τις επιστροφές.
     * @param input Η λιστα που θα φορτώσει
     */
    void loadSource(List<Quadruple> input);

    /**
     * Κατα την αλλαγή της κατάστασης ενός δανίου
     * εμφανίζεται ένα μήνυμα μορφής alert.
     * @param uid To μοναδικό id του δανιζόμενου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void newLoanStateSelectAlert(int uid, String title, String message);

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value σε μορφή alert.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showAlert(String title, String message);

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);

    /**
     * Κατα την ανανέωση της σελίδας
     * διαγράφετε η μπάρα αναζήτησης
     */
    void refresh();

    int getAttachedBorrowerID();

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);
}
