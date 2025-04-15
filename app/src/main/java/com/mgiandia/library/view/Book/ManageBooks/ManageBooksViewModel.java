package com.mgiandia.library.view.Book.ManageBooks;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ManageBooksViewModel extends ViewModel implements ButtonClicked
{
    private ManageBooksPresenter presenter;
    private BookDAO bookDAO = new BookDAOMemory();
    private final MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedBookID = new MutableLiveData<>();
    private final MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();
    private final MutableLiveData<Integer> attachedAuthorID = new MutableLiveData<>();
    private final MutableLiveData<Integer> attachedPublisherID = new MutableLiveData<>();

    public ManageBooksPresenter getPresenter(ManageBooksView view)
    {
        presenter = new ManageBooksPresenter(view, bookDAO, new AuthorDAOMemory(), new PublisherDAOMemory());
        return presenter;
    }

    public List<Book> getAllBooks()
    {
        return bookDAO.findAll();
    }

    public Set<Book> findBooks(String title)
    {
        return bookDAO.findByTitle(title);
    }

    public Set<Book> findBooks(String title, int authorID)
    {
        return bookDAO.findByTitleAndAuthorID(title, authorID);
    }

    public Set<Book> findBooksByTitleAndPublisherID(String title, int publisherID)
    {
        return bookDAO.findByTitleAndPublisherID(title, publisherID);
    }

    public Set<Book> findBooksByAuthorID(int authorID)
    {
        return bookDAO.findByAuthorID(authorID);
    }

    public Set<Book> findBooksByPublisherID(int publisherID)
    {
        return bookDAO.findByPublisherID(publisherID);
    }

    public Set<Book> findBooksByAuthorIDAndPublisherID(int authorID, int publisherID)
    {
        return bookDAO.findByAuthorIDAndPublisherID(authorID, publisherID);
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books.setValue(books);
    }

    public MutableLiveData<ArrayList<Book>> getBooks()
    {
        return books;
    }

    public void setSelectedBookID(int selectedBookID)
    {
        this.selectedBookID.setValue(selectedBookID);
    }

    public MutableLiveData<Integer> getSelectedBookID()
    {
        return selectedBookID;
    }

    public void setTextOnSearchBar(String textOnSearchBar)
    {
        this.textOnSearchBar.setValue(textOnSearchBar);
    }

    public MutableLiveData<String> getTextOnSearchBar()
    {
        return textOnSearchBar;
    }

    public void setAttachedAuthorID(int attachedAuthorID)
    {
        this.attachedAuthorID.setValue(attachedAuthorID);
    }

    public MutableLiveData<Integer> getAttachedAuthorID()
    {
        return attachedAuthorID;
    }

    public void setAttachedPublisherID(int attachedPublisherID)
    {
        this.attachedPublisherID.setValue(attachedPublisherID);
    }

    public MutableLiveData<Integer> getAttachedPublisherID()
    {
        return attachedPublisherID;
    }
}
