package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.BorrowerCategory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BorrowerCategoryDAO
{
    void save(BorrowerCategory entity);
    void delete(BorrowerCategory entity);
    BorrowerCategory find(int uid);
    List<BorrowerCategory> findAll();

    int nextId();
}
