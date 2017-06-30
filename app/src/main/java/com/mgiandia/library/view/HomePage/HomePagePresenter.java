package com.mgiandia.library.view.HomePage;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class HomePagePresenter
{
    private HomePageView view;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     */
    public HomePagePresenter(HomePageView view)
    {
        this.view = view;
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onManageBorrowers()
    {
        view.manageBorrowers();
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onManageBooks()
    {
        view.manageBooks();
    }

    /**
     * Όταν πραγματοποιείται click στο ManageAuthorsActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onManageAuthors()
    {
        view.manageAuthors();
    }

    /**
     * Όταν πραγματοποιείται click στο ManagePublishersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onManagePublishers()
    {
        view.managePublishers();
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν τα βιβλία.
     */
    void onManageItems()
    {
        view.manageItems();
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν τα δάνια.
     */
    void onManageLoans()
    {
        view.manageLoans();
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν οι επιστροφές.
     */
    void onManageReturns()
    {
        view.manageReturns();
    }
}
