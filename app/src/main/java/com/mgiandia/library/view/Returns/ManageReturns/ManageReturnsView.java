package com.mgiandia.library.view.Returns.ManageReturns;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageReturnsView
{
    void loadSource(List<Quadruple> input);

    void newLoanStateSelectAlert(int uid, String title, String message);

    void showAlert(String title, String message);
    void showToast(String value);

    void refresh();

    int getAttachedBorrowerID();
    void setPageName(String value);
}
