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

    public void delete(Book entity) {
        entities.remove(entity);
    }

    public List<Book> findAll() {
        ArrayList<Book> result = new ArrayList<Book>();
        result.addAll(entities);
        return result;
    }

    public void save(Book book) {
        entities.add(book);
    }

    public Book find(int uid)
    {
        for(Book now : entities)
            if(now.getId() == uid)
                return now;

        return null;
    }

    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
