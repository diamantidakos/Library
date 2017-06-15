package com.mgiandia.library.view.Book.BookDetails;

import java.util.ArrayList;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BookDetailsPresenter
{
    private BookDetailsView view;
    private Book attachedBook;

    public BookDetailsPresenter(BookDetailsView view, BookDAO books)
    {
        this.view = view;

        attachedBook = books.find(view.getAttachedBookID());

        view.setPageName("Βιβλίο #" + attachedBook.getId());

        view.setID("#"+attachedBook.getId());
        view.setBookTitle(attachedBook.getTitle());
        view.setPublisher(attachedBook.getPublisher().getName());
        view.setISBN(attachedBook.getIsbn().getValue());
        view.setPublication(attachedBook.getPublication());
        view.setYear(attachedBook.getPublicationYear()+"");

        int copiesWeHave = attachedBook.getItems().size();
        view.setItemsNo(copiesWeHave+" "+(copiesWeHave == 1 ? "Αντίτυπο" : "Αντίτυπα"));

        ArrayList<String> author_ids = new ArrayList<>(), author_names = new ArrayList<>();

        for(int i = 0; i < attachedBook.getAuthors().size(); i++)
            author_ids.add("#"+(i+1));

        for(Author author : attachedBook.getAuthors())
            author_names.add(author.getLastName()+" "+author.getFirstName());

        view.setAuthors(author_ids, author_names);
    }

    public void onStartEditButtonClick()
    {
        view.startEdit(attachedBook.getId());
    }

    public void onShowToast(String value)
    {
        view.showToast(value);
    }
}
