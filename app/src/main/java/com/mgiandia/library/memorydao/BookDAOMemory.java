package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BookDAOMemory implements BookDAO {
    protected static ArrayList<Book> entities = new ArrayList<Book>();

    /**
     * Διαγράφει ένα βιβλίο.
     * @param entity Το βιβλίο
     */
    public void delete(Book entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλα τα βιβλία.
     * @return Τα βιβλία
     */
    public List<Book> findAll() {
        ArrayList<Book> result = new ArrayList<Book>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει ένα βιβλίο.
     * @param entity Το βιβλίο
     */
    public void save(Book entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει ένα βιβλίο με βάση τον κωδικό του.
     * @param uid Ο κωδικός του βιβλίο
     * @return Το βιβλίο που βρέθηκε ή null
     */
    public Book find(int uid)
    {
        for(Book now : entities)
            if(now.getId() == uid)
                return now;

        return null;
    }

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε ένα βιβλίο.
     * @return Ο κωδικός του βιβλίου
     */
    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
