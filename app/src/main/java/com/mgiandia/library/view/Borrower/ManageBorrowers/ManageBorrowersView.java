package com.mgiandia.library.view.Borrower.ManageBorrowers;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageBorrowersView
{
    /**
     * Μεταφέρει τον χρήστη στο activity BorrowerDetailsActivity
     * όταν γίνει click πάνω στον δανειζόμενο με id uid.
     * @param uid Το μοναδικό id του δανειζόμενου
     */
    void clickItem(int uid);

    /**
     * Μεταφέρει τον χρήστη στο activity ManageLoansActivity
     * όταν γίνει click πάνω στον δανειζόμενο με id uid.
     * @param uid Το μοναδικό id του δανειζόμενου
     */
    void clickItemLoans(int uid);

    /**
     * Μεταφερει τον χρήστη στο activity ManageReturnsActivity
     * όταν γίνει click πάνω στον δανειζόμενο με id uid.
     * @param uid Το μοναδικό id του δανειζόμενου
     */
    void clickItemReturns(int uid);

    /**
     * Ξεκινάει το activity AddEditBorrowerActivity
     */
    void startAddNew();

    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     * @param input Η λίστα με τους δανειζομένους σε μορφή List.
     */
    void loadSource(List<Quadruple> input);

    /**
     * Αν πρέπει να επιστρέψει τα δάνεια
     * @return Τα επιστρεφόμενα δάνεια
     */
    boolean shouldLoadLoansOnClick();

    /**
     * Αν πρέπει να επιστρέψει τις επιστροφές
     * @return Οι επιστροφές
     */
    boolean shouldLoadReturnsOnClick();

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
