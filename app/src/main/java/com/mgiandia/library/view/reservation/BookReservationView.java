package com.mgiandia.library.view.reservation;

public interface BookReservationView {
    void showSearchDialog(String bookTitle, String authorName);

    void showError(String msg);

    void showStatus(String msg);
}
