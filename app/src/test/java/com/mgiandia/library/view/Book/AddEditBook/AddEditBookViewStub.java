package com.mgiandia.library.view.Book.AddEditBook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBookViewStub implements AddEditBookView
{
    private String title, isbn, year, publication, pageName, errorTitle, errorMessage, finishMessage;
    private Integer position, attachedBookID;
    private List<Integer> authorsPositions;
    private List<String> authorsNames, publishersNames;

    private AddEditBookPresenter presenter;

    public void setPresenter(AddEditBookPresenter presenter) {
        this.presenter = presenter;
    }

    public AddEditBookPresenter getPresenter() {
        return presenter;
    }

    public AddEditBookViewStub()
    {
        title = isbn = year = publication = pageName = errorTitle = errorMessage = finishMessage = "";
        authorsPositions = new ArrayList<Integer>();
        authorsNames = new ArrayList<String>();
        publishersNames = new ArrayList<String>();
    }

    public void setAttachedBookID(Integer val)
    {
        attachedBookID = val;
    }

    public List<String> getAuthorList()
    {
        return authorsNames;
    }

    public List<String> getPublisherList()
    {
        return publishersNames;
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

    public String getBookTitle()
    {
        return title;
    }

    public Integer getPublisherPosition()
    {
        return position;
    }

    public String getISBN()
    {
        return isbn;
    }

    public String getPublication()
    {
        return publication;
    }

    public String getYear()
    {
        return year;
    }

    public List<Integer> getAuthorPositions()
    {
        return authorsPositions;
    }

    public Integer getAttachedBookID()
    {
        return attachedBookID;
    }

    public void setBookTitle(String value)
    {
        title = value;
    }

    public void setPublisherPosition(Integer value)
    {
        position = value;
    }

    public void setISBN(String value)
    {
        isbn = value;
    }

    public void setPublication(String value)
    {
        publication = value;
    }

    public void setYear(String value)
    {
        year = value;
    }

    public void setAuthorPositions(List<Integer> value)
    {
        authorsPositions = value;
    }

    public void setPageName(String value)
    {
        pageName = value;
    }

    public void setAuthorList(List<String> names)
    {
        authorsNames = names;
    }

    public void setPublisherList(List<String> names, String defaultName)
    {
        publishersNames = names;
    }

    public void successfullyFinishActivity(String message)
    {
        finishMessage = message;
    }

    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }
}
