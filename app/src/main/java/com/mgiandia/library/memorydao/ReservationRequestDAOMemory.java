package com.mgiandia.library.memorydao;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.ReservationRequestDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.ReservationRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ReservationRequestDAOMemory implements ReservationRequestDAO {

    protected static ArrayList<ReservationRequest> entities = new ArrayList<ReservationRequest>();

    /**
     * Αποθηκεύει ένα αίτημα κράτησης
     * @param entity Το αίτημα κράτησης
     */
    public void save(ReservationRequest entity) {
        entities.add(entity);
    }

    @Override
    public List<ReservationRequest> findAll(){
        return entities;
    }

}
