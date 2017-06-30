package com.mgiandia.library.view.Loans.ManageLoans;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageLoansView
{
    /**
     * @param input Η πηγή ως List.
     * Φορτώνει την λίστα με τους δανιζόμενους.
     */
    void loadSource(List<Quadruple> input);

    /**
     * @param uid Ο μοναδικός κωδικός.
     * Ξεκινάει το activity AddLoansActivity
     */
    void startAddNew(int uid);

    /**
     * Εμφανίζει ενα μήνυμα σε μορφή alert
     * με περιεχόμενο message και τίτλο title.
     * @param title Ο τίτλος του μηνύματος
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
     * Επιστρέφει το id του δανιζόμενου
     * @return Το id του δανιζόμενου
     */
    int getAttachedBorrowerID();

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);
}
