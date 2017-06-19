package com.mgiandia.library.view.Author.AuthorDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AuthorDetailsView
{
    int getAttachedAuthorID();

    void setID(String value);
    void setFirstName(String value);
    void setLastName(String value);
    void setBooksWritten(String value);

    void setPageName(String value);

    void startEdit(int authorID);
    void startShowBooks(int authorID);

    void showToast(String value);
}
