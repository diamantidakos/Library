package com.mgiandia.library.view.HomePage;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface HomePageView
{
    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void manageBorrowers();

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void manageBooks();

    /**
     * Όταν πραγματοποιείται click στο ManageAuthorsActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void manageAuthors();

    /**
     * Όταν πραγματοποιείται click στο ManagePublishersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void managePublishers();

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν τα βιβλία.
     */
    void manageItems();

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν τα δάνεια.
     */
    void manageLoans();

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν οι επιστροφές.
     */
    void manageReturns();
}
