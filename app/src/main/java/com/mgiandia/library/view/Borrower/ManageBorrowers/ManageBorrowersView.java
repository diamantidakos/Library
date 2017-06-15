package com.mgiandia.library.view.Borrower.ManageBorrowers;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageBorrowersView
{
    void clickItem(int uid);
    void clickItemLoans(int uid);
    void clickItemReturns(int uid);

    void startAddNew();
    void loadSource(List<Quadruple> input);

    boolean shouldLoadLoansOnClick();
    boolean shouldLoadReturnsOnClick();

    void showToast(String value);
}
