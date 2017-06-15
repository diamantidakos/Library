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

    void onStartEditButtonClick()
    {
        view.startEdit(attachedAuthor.getId());
    }

    void onStartShowBooksButtonClick()
    {
        view.startShowBooks(attachedAuthor.getId());
    }

    void onShowToast(String value)
    {
        view.showToast(value);
    }
}
