package com.mgiandia.library.view.reservation;

import android.util.Log;

public class BookReservationPresenter {

    private BookReservationView view;

    public BookReservationView getView() {
        return view;
    }

    public void setView(BookReservationView view) {
        this.view = view;
    }

    public void search(String bookTitle, String authorName) {
        Log.d("BookResPresenter", "search book");
    }

    public void submitReservationRequest(String borrowerId) {
        Log.d("BookResPresenter", "submit reservation request");
    }
}
