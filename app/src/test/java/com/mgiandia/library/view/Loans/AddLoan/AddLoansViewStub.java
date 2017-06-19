package com.mgiandia.library.view.Loans.AddLoan;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorPresenter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddLoansViewStub implements AddLoansView
{
    private String borrowerID, pageName, errorTitle, errorMessage, finishMessage;
    private int attachedBorrowerID, selectedBookId;
    private List<String> bookList;

    private AddEditAuthorPresenter presenter;

    public void setAttachedBorrowerID(int val)
    {
        attachedBorrowerID = val;
    }

    public void setSelectedBookId(int value)
    {
        selectedBookId = value;
    }

    public void setBorrowerId(String value)
    {
        borrowerID = value;
    }

    public int getAttachedBorrowerID()
    {
        return attachedBorrowerID;
    }

    public int getSelectedBookId()
    {
        return selectedBookId;
    }

    public void setPageName(String value)
    {
        pageName = value;
    }

    public void setPresenter(AddEditAuthorPresenter presenter) {
        this.presenter = presenter;
    }

    public void setBookList(List<String> value)
    {
        bookList = value;
    }

    public AddEditAuthorPresenter getPresenter() {
        return presenter;
    }

    public AddLoansViewStub()
    {
        borrowerID = pageName = errorTitle = errorMessage = finishMessage = "";
        bookList = new ArrayList<>();
    }

    public String getPageName()
    {
        return pageName;
    }

    public String getErrorTitle()
    {
        return errorTitle;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String getFinishMessage()
    {
        return finishMessage;
    }

    public List<String> getBookList()
    {
        return bookList;
    }

    public void successfullyAddLoanAndFinishActivity(String message)
    {
        finishMessage = message;
    }

    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }

    public void showAlert(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }
}
