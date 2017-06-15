package com.mgiandia.library.view.Book.ManageBooks;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageBooksPresenter
{
    private ManageBooksView view;

    private BookDAO books;
    private AuthorDAO authors;
    private PublisherDAO publishers;

    private Integer attachedAuthorID, attachedPublisherID;

    private List<Quadruple> createDataSource(List<Book> books)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Book book : books)
            triplets.add(new Quadruple(book.getId(), "Από "+book.getPublisher().getName()+", "+book.getPublicationYear(), book.getTitle(), "Κωδικός: "+book.getId()+".  Συγγραφείς: "+book.getAuthors().size()));

        return triplets;
    }

    public ManageBooksPresenter(ManageBooksView view, BookDAO books, AuthorDAO authors, PublisherDAO publishers)
    {
        this.view = view;
        this.books = books;
        this.authors = authors;
        this.publishers = publishers;

        this.attachedAuthorID = view.getAttachedAuthorID();
        this.attachedPublisherID = view.getAttachedPublisherID();

        if(attachedAuthorID != null)
            view.setPageName("Βιβλία Συγγραφέα #" + attachedAuthorID);
        else if(attachedPublisherID != null)
            view.setPageName("Βιβλία Εκδοτικού Οίκου #" + attachedPublisherID);

        onLoadSource();
    }

    void onClickItem(int uid)
    {
        if(!view.shouldLoadItemsOnClick())
            view.clickItem(uid);
        else
            view.clickItemList(uid);
    }

    void onStartAddNew()
    {
        view.startAddNew();
    }

    void onShowToast(String value)
    {
        view.showToast(value);
    }

    void onLoadSource()
    {
        List<Book> toReturn;

        if(attachedAuthorID != null)
            toReturn = new ArrayList<>(authors.find(attachedAuthorID).getBooks());
        else if(attachedPublisherID != null)
            toReturn = new ArrayList<>(publishers.find(attachedPublisherID).getBooks());
        else
            toReturn = books.findAll();

        view.loadSource(createDataSource(toReturn));
    }
}
