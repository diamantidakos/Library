package com.mgiandia.library.view.Book.ManageBooks;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageBooksView
{
    void clickItem(int uid);
    void clickItemList(int uid);

    void startAddNew();
    void loadSource(List<Quadruple> input);

    void setPageName(String value);

    void showToast(String value);

    boolean shouldLoadItemsOnClick();

    Integer getAttachedAuthorID();
    Integer getAttachedPublisherID();
}
