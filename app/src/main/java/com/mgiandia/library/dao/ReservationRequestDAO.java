package com.mgiandia.library.dao;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.ReservationRequest;

import java.util.List;

public interface ReservationRequestDAO {

    void save(ReservationRequest entity);

}
