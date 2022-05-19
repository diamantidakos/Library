package com.mgiandia.library.view.reservation;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class BookReservationViewModel extends ViewModel {

    private BookReservationPresenter presenter;

    public BookReservationViewModel() {
        presenter = new BookReservationPresenter();
    }

    public BookReservationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("BookReservationVM", "On Cleared");
    }
}
