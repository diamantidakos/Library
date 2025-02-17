package com.mgiandia.library.view.Book.BookDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookDetailsViewModel extends ViewModel implements ButtonClicked
{
    BookDAO bookDAO = new BookDAOMemory();

    public BookDetailsPresenter getPresenter(BookDetailsView view)
    {
        return new BookDetailsPresenter(view, bookDAO);
    }

    public Book findBook(int bookID)
    {
        return bookDAO.find(bookID);
    }
    private final MutableLiveData<String> bookID = new MutableLiveData<>();
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> publisher = new MutableLiveData<>();
    private final MutableLiveData<String> ISBN = new MutableLiveData<>();
    private final MutableLiveData<String> publication = new MutableLiveData<>();
    private final MutableLiveData<String> publicationYear = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>> authors = new MutableLiveData<>();
    private final MutableLiveData<Integer> publisherPosition = new MutableLiveData<>();
    private final MutableLiveData<List<Integer>> selectedAuthors = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Set<Item>> copies = new MutableLiveData<>();
    private final MutableLiveData<Integer> copiesNum = new MutableLiveData<>();

    public void setBookID(String bookID)
    {
        this.bookID.setValue(bookID);
    }

    public LiveData<String> getBookID()
    {
        return bookID;
    }

    public void setTitle(String t)
    {
        title.setValue(t);
    }

    public MutableLiveData<String> getTitle()
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

    public void addAuthor(String newAuthor)
    {
        if (newAuthor == null || newAuthor.trim().isEmpty())
        {
            return;
        }

        ArrayList<String> currentAuthors = authors.getValue();
        if (currentAuthors == null)
        {
            currentAuthors = new ArrayList<>();
        }

        if (!currentAuthors.contains(newAuthor))
        {
            currentAuthors.add(newAuthor);
            authors.setValue(currentAuthors);
        }
    }

    public void setPublisherPosition(int pos)
    {
        publisherPosition.setValue(pos);
    }

    public MutableLiveData<Integer> getPublisherPosition()
    {
        return publisherPosition;
    }

    public void setSelectedAuthorsPositions(int index)
    {
        List<Integer> currentIndexes = new ArrayList<>(selectedAuthors.getValue());
        if (currentIndexes.contains(index))
        {
            currentIndexes.remove(index);
        }
        else
        {
            currentIndexes.add(index);
        }

        selectedAuthors.setValue(currentIndexes);
    }

    public void setSelectedAuthorsPositions(List<Integer> indexes)
    {
        selectedAuthors.setValue(indexes);
    }

    public MutableLiveData<List<Integer>> getSelectedAuthorsPositions()
    {
        return selectedAuthors;
    }

    public void setCopies(Set<Item> copies)
    {
        this.copies.setValue(copies);
        this.copiesNum.setValue(copies.size());
    }

    public MutableLiveData<Set<Item>> getCopies()
    {
        return copies;
    }

    public MutableLiveData<Integer> getCopiesNum()
    {
        return copiesNum;
    }
}
