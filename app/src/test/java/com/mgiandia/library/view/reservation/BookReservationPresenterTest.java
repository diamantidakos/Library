package com.mgiandia.library.view.reservation;

import static org.junit.Assert.*;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.ReservationRequest;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.ReservationRequestDAOMemory;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookReservationPresenterTest {

    BookReservationViewStub viewStub;
    BookReservationPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new BookReservationViewStub();
        presenter = new BookReservationPresenter();
        presenter.setView(viewStub);
        presenter.setBookDAO(new BookDAOMemory());
        presenter.setBorrowerDAO(new BorrowerDAOMemory());
        presenter.setReservationRequestDAO(new ReservationRequestDAOMemory());
    }

    @Test
    public void showError_whenNoBookSelected(){
        presenter.submitReservationRequest("1");
        assertEquals(1, viewStub.getErrorCount());
        assertEquals("Invalid book selection", viewStub.getErrorMsg());
    }

    @Test
    public void showError_onInvalidBorrowerId(){
        presenter.submitReservationRequest("1a");
        assertEquals(1, viewStub.getErrorCount());
        assertEquals("Invalid borrower id", viewStub.getErrorMsg());
    }

    @Test
    public void successfulReservationRequest(){
        // Book 2 has not available items
        presenter.selectBook(2);
        // Borrower 2 has not borrowed any items
        presenter.submitReservationRequest("2");
        assertEquals(0, viewStub.getErrorCount());
        assertEquals("Reservation request submitted successfully!", viewStub.getStatusMsg());

        List<ReservationRequest> requestList = new ReservationRequestDAOMemory().findAll();
        assertEquals(1, requestList.size());
    }

    @Test
    public void showError_onDeniedReservationRequest(){
        presenter.selectBook(3);
        // Borrower 2 has not borrowed any items
        presenter.submitReservationRequest("2");
        assertEquals(1, viewStub.getErrorCount());
        assertEquals("Reservation request rejected", viewStub.getErrorMsg());

    }
}