package com.mgiandia.library.view.Author.AddEditAuthor;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditAuthorView
{
    String getFirstName();
    String getLastName();

    Integer getAttachedAuthorID();

    void setFirstName(String value);
    void setLastName(String value);

    void setPageName(String value);

    void successfullyFinishActivity(String message);
    void showErrorMessage(String title, String message);
}