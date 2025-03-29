package com.mgiandia.library.view.Book.AddEditBook;

import androidx.compose.runtime.State;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddEditBookViewModel extends ViewModel implements ButtonClicked
{
    private BookDAO bookDAO = new BookDAOMemory();
    private PublisherDAO publisherDAO = new PublisherDAOMemory();
    private AuthorDAO authorDAO = new AuthorDAOMemory();
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> publisher = new MutableLiveData<>();
    private final MutableLiveData<String> ISBN = new MutableLiveData<>();
    private final MutableLiveData<String> publication = new MutableLiveData<>();
    private final MutableLiveData<String> publicationYear = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>> authors = new MutableLiveData<>();
    private final MutableLiveData<Integer> publisherPosition = new MutableLiveData<>();
    private final MutableLiveData<List<Integer>> selectedAuthors = new MutableLiveData<>(new ArrayList<>());

    public AddEditBookPresenter getPresenter(AddEditBookView view)
    {
        return new AddEditBookPresenter(view, bookDAO, publisherDAO, authorDAO, new ItemDAOMemory());
    }

    public void setTitle(String t)
    {
        title.setValue(t);
    }

    public LiveData<String> getTitle()
    {
        return title;
    }

    public void setPublisher(String p)
    {
        publisher.setValue(p);
    }

    public MutableLiveData<String> getPublisher()
    {
        return publisher;
    }

    public void setISBN(String isbn)
    {
        ISBN.setValue(isbn);
    }

    public MutableLiveData<String> getISBN() {
        return ISBN;
    }

    public void setPublication(String p)
    {
        publication.setValue(p);
    }

    public MutableLiveData<String> getPublication()
    {
        return publication;
    }

    public void setPublicationYear(String py)
    {
        publicationYear.setValue(py);
    }

    public MutableLiveData<String> getPublicationYear()
    {
        return publicationYear;
    }

    public void setAuthors(ArrayList<String> a)
    {
        authors.setValue(a);
    }

    public MutableLiveData<ArrayList<String>> getAuthors()
    {
        return authors;
    }

    public void toggleAuthor(String authorName)
    {
        if (authorName == null || authorName.trim().isEmpty() || authors.getValue() == null)
        {
            return;
        }

        ArrayList<String> currentAuthors = authors.getValue();
        if (currentAuthors.contains(authorName))
        {
            currentAuthors.remove(authorName);
        }
        else
        {
            currentAuthors.add(authorName);
        }

        authors.setValue(currentAuthors);
    }

    public void setPublisherPosition(int pos)
    {
        publisherPosition.setValue(pos);
    }

    public MutableLiveData<Integer> getPublisherPosition()
    {
        return publisherPosition;
    }

    public void setSelectedAuthorsPositions(List<Integer> indexes)
    {
        selectedAuthors.setValue(indexes);
    }

    public LiveData<List<Integer>> getSelectedAuthorsPositions()
    {
        return selectedAuthors;
    }

    public Book findBook(int bookID)
    {
        return bookDAO.find(bookID);
    }

    public ArrayList<String> findAllPublisherNames()
    {
        ArrayList<String> result = new ArrayList<>();
        List<Publisher> allPublishers = publisherDAO.findAll();

        for (Publisher publisher : allPublishers)
        {
            result.add(publisher.getName());
        }

        return result;
    }

    public ArrayList<String> findAllAuthorNames()
    {
        ArrayList<String> result = new ArrayList<>();
        List<Author> allAuthors = authorDAO.findAll();

        for (Author author : allAuthors)
        {
            result.add(author.getFirstName() + " " + author.getLastName());
        }

        return result;
    }
}
