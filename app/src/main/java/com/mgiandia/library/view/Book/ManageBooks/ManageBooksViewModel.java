package com.mgiandia.library.view.Book.ManageBooks;

import android.widget.ListView;
import android.widget.SearchView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;
import com.mgiandia.library.view.Items.ManageItems.ManageItemsPresenter;
import com.mgiandia.library.view.Items.ManageItems.ManageItemsView;

import java.util.ArrayList;
import java.util.List;

public class ManageBooksViewModel extends ViewModel implements ButtonClicked
{
    private ManageBooksPresenter presenter;
    private BookDAO bookDAO = new BookDAOMemory();
    private final MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedBookID = new MutableLiveData<>();

    public ManageBooksPresenter getPresenter(ManageBooksView view)
    {
        presenter = new ManageBooksPresenter(view, bookDAO, new AuthorDAOMemory(), new PublisherDAOMemory());
        return presenter;
    }

    public List<Book> getAllBooks()
    {
        return bookDAO.findAll();
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
}
