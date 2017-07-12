package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AuthorDAOMemory implements AuthorDAO {
    protected static ArrayList<Author> entities = new ArrayList<Author>();

    /**
     * Διαγράφει έναν συγγραφέα.
     * @param entity Ο συγγραφέας
     */
    public void delete(Author entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλους τους συγγραφείς.
     * @return Οι συγγραφείς
     */
    public List<Author> findAll() {
        ArrayList<Author> result = new ArrayList<Author>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει έναν συγγραφέα.
     * @param entity Ο συγγραφέας
     */
    public void save(Author entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει έναν συγγραφέα με βάση τον κωδικό του.
     * @param authorΙd Ο κωδικός του συγγραφέα
     * @return Ο συγγραφέας που βρέθηκε ή null
     */
    public Author find(int authorΙd)
    {
        for(Author author : entities)
            if(author.getId() == authorΙd)
                return author;

        return null;
    }

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε έναν συγγραφέα.
     * @return Ο κωδικός του συγγραφέα
     */
    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
