package com.mgiandia.library.view.Book.BookDetails;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BookDetailsView
{
    int getAttachedBookID();

    void setID(String value);
    void setBookTitle(String value);
    void setPublisher(String value);
    void setISBN(String value);
    void setPublication(String value);
    void setYear(String value);
    void setItemsNo(String value);
    void setAuthors(List<String> author_ids, List<String> author_names);

    void setPageName(String value);

    void startEdit(int bookID);

    void showToast(String value);
}
