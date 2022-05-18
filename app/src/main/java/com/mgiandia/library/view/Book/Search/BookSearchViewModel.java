package com.mgiandia.library.view.Book.Search;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.memorydao.BookDAOMemory;

public class BookSearchViewModel extends ViewModel {

    private BookSearchPresenter presenter;

    public BookSearchViewModel() {
        presenter = new BookSearchPresenter();
        BookDAO bookDAO = new BookDAOMemory();
        presenter.setBookDAO(bookDAO);
    }

    public BookSearchPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}

