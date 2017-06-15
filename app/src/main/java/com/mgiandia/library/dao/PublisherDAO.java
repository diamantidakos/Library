package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.Publisher;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface PublisherDAO
{
    Publisher find(int author_id);
    List<Publisher> findAll();
    void save(Publisher entity);
    void delete(Publisher entity);

    int nextId();
}
