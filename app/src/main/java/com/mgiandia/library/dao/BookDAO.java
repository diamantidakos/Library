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
    Book find(int book_id);
    List<Book> findAll();
    void save(Book entity);
    void delete(Book entity);

    int nextId();
}
