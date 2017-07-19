package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.Book;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BookDAO
{
    /**
     * Διαγράφει ένα βιβλίο.
     * @param entity Το βιβλίο
     */
    void delete(Book entity);

    /**
     * Επιστρέφει όλα τα βιβλία.
     * @return Τα βιβλία
     */
    List<Book> findAll();

    /**
     * Αποθηκεύει ένα βιβλίο.
     * @param entity Το βιβλίο
     */
    void save(Book entity);

    /**
     * Βρίσκει ένα βιβλίο με βάση τον κωδικό του.
     * @param uid Ο κωδικός του βιβλίο
     * @return Το βιβλίο που βρέθηκε ή null
     */
    Book find(int uid);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε ένα βιβλίο.
     * @return Ο κωδικός του βιβλίου
     */
    int nextId();
}
