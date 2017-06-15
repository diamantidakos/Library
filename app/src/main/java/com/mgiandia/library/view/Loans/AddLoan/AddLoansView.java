package com.mgiandia.library.view.Loans.AddLoan;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddLoansView
{
    int getSelectedBookId();
    int getAttachedBorrowerID();

    void setBorrowerId(String value);

    void setPageName(String value);

    void successfullyAddLoanAndFinishActivity(String message);

    void showErrorMessage(String title, String message);
    void showAlert(String title, String message);

    void setBookList(List<String> names);
}
