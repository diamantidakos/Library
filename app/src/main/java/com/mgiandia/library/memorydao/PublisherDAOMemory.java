package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherDAOMemory implements PublisherDAO {
    protected static ArrayList<Publisher> entities = new ArrayList<Publisher>();

    public List<Publisher> findAll() {
        ArrayList<Publisher> result = new ArrayList<Publisher>();
        result.addAll(entities);
        return result;
    }

    public void delete(Publisher entity) {
        entities.remove(entity);
    }

    public void save(Publisher entity) {
        entities.add(entity);
    }

    public Publisher find(int publisher_id)
    {
        for(Publisher publisher : entities)
            if(publisher.getId() == publisher_id)
                return publisher;

        return null;
    }

    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
