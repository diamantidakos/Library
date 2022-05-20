package com.mgiandia.library.view.reservation;

import android.util.Log;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ReservationRequestDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.ReservationRequest;

public class BookReservationPresenter {

    private BookReservationView view;
    private BorrowerDAO borrowerDAO;
    private ReservationRequestDAO reservationRequestDAO;
    private BookDAO bookDAO;
    private Book book;

    public BookReservationView getView() {
        return view;
    }

    public void setView(BookReservationView view) {
        this.view = view;
    }

    public void search(String bookTitle, String authorName) {
        view.showSearchDialog(bookTitle, authorName);
    }

    public void selectBook(int bookId){
        book = bookDAO.find(bookId);
        if (book == null){
            view.showError("Invalid book selection");
            return;
        }
        view.showStatus("Selected book with id " + bookId);
    }

    public void submitReservationRequest(String borrowerId) {
        if (borrowerId == null || !isInteger(borrowerId)){
            view.showError("Invalid borrower id");
            return;
        }
        if (book == null){
            view.showError("Invalid book selection");
            return;
        }
        int id = Integer.parseInt(borrowerId);
        Borrower borrower = borrowerDAO.find(id);
        ReservationRequest request = book.reserve(borrower);
        if (request == null){
            view.showError("Reservation request rejected");
            return;
        }
        reservationRequestDAO.save(request);
        view.showStatus("Reservation request submitted successfully!");
    }

    private boolean isInteger(String num){
        try {
            int intValue = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
        this.borrowerDAO = borrowerDAO;
    }

    public void setReservationRequestDAO(ReservationRequestDAO reservationRequestDAO) {
        this.reservationRequestDAO = reservationRequestDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
