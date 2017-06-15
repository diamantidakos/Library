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

    public void delete(Author entity) {
        entities.remove(entity);
    }

    public List<Author> findAll() {
        ArrayList<Author> result = new ArrayList<Author>();
        result.addAll(entities);
        return result;
    }

    public void save(Author entity) {
        entities.add(entity);
    }

    public Author find(int author_id)
    {
        for(Author author : entities)
            if(author.getId() == author_id)
                return author;

        return null;
    }

    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
