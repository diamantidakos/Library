package com.mgiandia.library.view.reservation;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ReservationRequestDAOMemory;

public class BookReservationViewModel extends ViewModel {

    private BookReservationPresenter presenter;

    public BookReservationViewModel() {
        presenter = new BookReservationPresenter();
        presenter.setBookDAO(new BookDAOMemory());
        presenter.setBorrowerDAO(new BorrowerDAOMemory());
        presenter.setReservationRequestDAO(new ReservationRequestDAOMemory());
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
