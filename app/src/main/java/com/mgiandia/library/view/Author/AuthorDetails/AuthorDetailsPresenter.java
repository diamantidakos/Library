package com.mgiandia.library.view.Author.AuthorDetails;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AuthorDetailsPresenter
{
    private AuthorDetailsView view;
    private Author attachedAuthor;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param authors Ένα instance του author
     */
    public AuthorDetailsPresenter(AuthorDetailsView view, AuthorDAO authors)
    {
        this.view = view;

        attachedAuthor = authors.find(view.getAttachedAuthorID());

        view.setPageName("Συγγραφέας #" + attachedAuthor.getId());

        view.setID("#"+attachedAuthor.getId());
        view.setFirstName(attachedAuthor.getFirstName());
        view.setLastName(attachedAuthor.getLastName());

        int booksWritten = attachedAuthor.getBooks().size();
        view.setBooksWritten(booksWritten+" "+(booksWritten == 1 ? "Βιβλίο" : "Βιβλία"));
    }

    /**
     * Εμφανίζει το κουμπί της επεξεργασίας του
     * συγγραφέα.
     */
    void onStartEditButtonClick()
    {
        view.startEdit(attachedAuthor.getId());
    }

    /**
     * Το κουμπί που θα εμφανίσει όλα
     * τα βιβλία του συγγραφέα.
     */
    void onStartShowBooksButtonClick()
    {
        view.startShowBooks(attachedAuthor.getId());
    }

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο value.
     * @param value Το περιεχόμενο του μηνύματος
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }
}
