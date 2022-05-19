package com.mgiandia.library.domain;

import com.mgiandia.library.util.SimpleCalendar;

public class ReservationRequest {
    private Borrower borrower;
    private Book book;
    private SimpleCalendar requestDate;

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public SimpleCalendar getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(SimpleCalendar requestDate) {
        this.requestDate = requestDate;
    }
}
