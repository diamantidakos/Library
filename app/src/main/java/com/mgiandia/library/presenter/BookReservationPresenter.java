package com.mgiandia.library.presenter;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;

public class BookReservationPresenter {

    private BookReservationView view;

    private Book selectedBook;

    public BookReservationPresenter(BookReservationView view) {
        this.view = view;
    }

    public void selectBook(int bookId){
        BookDAO bookDAO = new BookDAOMemory();
        Book foundBook = bookDAO.find(bookId);
        if (foundBook == null){
            view.showError("Book not found");
        } else {
            selectedBook = foundBook;
        }
    }
}
