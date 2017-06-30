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
     * Μεταφερει τον χρήστη στο activity BorrowerDetailsActivity
     * όταν γίνει click πάνω στον δανιζόμενο με id uid.
     * @param uid To μοναδικό id του δανιζόμενου
     */
    void clickItem(int uid);

    /**
     * Μεταφερει τον χρήστη στο activity ManageLoansActivity
     * όταν γίνει click πάνω στον δανιζόμενο με id uid.
     * @param uid To μοναδικό id του δανιζόμενου
     */
    void clickItemLoans(int uid);

    /**
     * Μεταφερει τον χρήστη στο activity ManageReturnsActivity
     * όταν γίνει click πάνω στον δανιζόμενο με id uid.
     * @param uid To μοναδικό id του δανιζόμενου
     */
    void clickItemReturns(int uid);

    /**
     * Ξεκινάει το activity AddEditBorrowerActivity
     */
    void startAddNew();

    /**
     * Φορτώνει την λίστα με τους δανιζόμενους.
     * @param input Η λίστα με τους δανιζόμενους σε μορφή List.
     */
    void loadSource(List<Quadruple> input);

    /**
     * Αν πρέπει να επιστρέψει τα δάνια
     * @return Τα επιστραφόμενα δάνια
     */
    boolean shouldLoadLoansOnClick();

    /**
     * Αν πρέπει να επιστρέψει τις επιστροφές
     * @return Οι επιστροφές
     */
    boolean shouldLoadReturnsOnClick();

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
