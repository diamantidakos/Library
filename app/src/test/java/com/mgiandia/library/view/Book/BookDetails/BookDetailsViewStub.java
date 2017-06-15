package com.mgiandia.library.view.Book.BookDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BookDetailsViewStub implements BookDetailsView {
    private String title, publisher, isbn, publication, year, itemsNo, id, toast, pageName;
    private int attachedBookID, editBook;
    private List<String> authors;

    public BookDetailsViewStub() {
        title = publisher = isbn = publication = year = itemsNo = id = toast = pageName = "";
        attachedBookID = editBook = 0;
        authors = new ArrayList<String>();
    }

    public void setAttachedBookID(int id) {
        attachedBookID = id;
    }

    @Override
    public int getAttachedBookID() {
        return attachedBookID;
    }

    public String getID() {
        return id;
    }

    @Override
    public void setID(String value) {
        id = value;
    }

    @Override
    public void setPageName(String value) {
        pageName = value;
    }

    public int getEditBook() {
        return editBook;
    }

    @Override
    public void startEdit(int bookID) {
        editBook = bookID;
    }

    public String getToast() {
        return toast;
    }

    @Override
    public void showToast(String value) {
        toast = value;
    }

    @Override
    public void setBookTitle(String value) {
        title = value;
    }

    public String getBookTitle() {
        return title;
    }

    @Override
    public void setPublisher(String value) {
        publisher = value;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public void setISBN(String value) {
        isbn = value;
    }

    public String getISBN() {
        return isbn;
    }

    @Override
    public void setPublication(String value) {
        publication = value;
    }

    public String getPublication() {
        return publication;
    }

    @Override
    public void setYear(String value) {
        year = value;
    }

    public String getYear() {
        return year;
    }

    @Override
    public void setItemsNo(String value) {
        itemsNo = value;
    }

    public String getItemsNo() {
        return itemsNo;
    }

    @Override
    public void setAuthors(List<String> authorIds, List<String> authorNames)
    {
        authors = authorNames;
    }

    public List<String> getAuthors()
    {
        return authors;
    }
}
