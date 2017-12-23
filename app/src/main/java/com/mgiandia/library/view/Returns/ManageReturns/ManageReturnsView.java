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
     * @param input Η λίστα που θα φορτώσει
     */
    void loadSource(List<Quadruple> input);

    /**
     * Κατά την αλλαγή της κατάστασης ενός δανείου
     * εμφανίζεται ένα μήνυμα μορφής alert.
     * @param uid To μοναδικό id του δανειζόμενου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void newLoanStateSelectAlert(int uid, String title, String message);

    /**
     * Εμφανίζει ένα alert.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showAlert(String title, String message);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);

    /**
     * Κατά την ανανέωση της σελίδας
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
