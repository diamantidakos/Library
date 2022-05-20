package com.mgiandia.library.view.reservation;

public class BookReservationViewStub implements BookReservationView {

    private int errorCount;
    private int statusCount;
    private String errorMsg;
    private String statusMsg;
    @Override
    public void showSearchDialog(String bookTitle, String authorName) {

    }

    @Override
    public void showError(String msg) {
        this.errorMsg = msg;
        errorCount++;
    }

    @Override
    public void showStatus(String msg) {
        this.statusMsg = msg;
        statusCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getStatusCount() {
        return statusCount;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}
