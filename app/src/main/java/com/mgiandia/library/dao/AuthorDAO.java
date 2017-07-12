package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.Author;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AuthorDAO
{
    /**
     * Διαγράφει έναν συγγραφέα.
     * @param entity Ο συγγραφέας
     */
    void delete(Author entity);

    /**
     * Επιστρέφει όλους τους συγγραφείς.
     * @return Οι συγγραφείς
     */
    List<Author> findAll();

    /**
     * Αποθηκεύει έναν συγγραφέα.
     * @param entity Ο συγγραφέας
     */
    void save(Author entity);

    /**
     * Βρίσκει έναν συγγραφέα με βάση τον κωδικό του.
     * @param authorId Ο κωδικός του συγγραφέα
     * @return Ο συγγραφέας που βρέθηκε ή null
     */
    Author find(int authorId);

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε έναν συγγραφέα.
     * @return Ο κωδικός του συγγραφέα
     */
    int nextId();
}
