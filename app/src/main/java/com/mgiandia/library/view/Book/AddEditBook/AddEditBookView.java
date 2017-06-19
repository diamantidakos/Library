package com.mgiandia.library.view.Book.AddEditBook;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditBookView
{
    String getBookTitle();
    Integer getPublisherPosition();
    String getISBN();
    String getPublication();
    String getYear();
    List<Integer> getAuthorPositions();

    Integer getAttachedBookID();

    void setBookTitle(String value);
    void setPublisherPosition(Integer value);
    void setISBN(String value);
    void setPublication(String value);
    void setYear(String value);
    void setAuthorPositions(List<Integer> value);

    void setPageName(String value);

    void setAuthorList(List<String> names);
    void setPublisherList(List<String> names, String defaultName);

    void successfullyFinishActivity(String message);
    void showErrorMessage(String title, String message);
}
