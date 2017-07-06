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

    /**
     * Επιστρέφει όλους τους εκδοτικούς οίκους.
     * @return Οι εκδοτικοί οίκοι
     */
    public List<Publisher> findAll() {
        ArrayList<Publisher> result = new ArrayList<Publisher>();
        result.addAll(entities);
        return result;
    }

    /**
     * Διαγράφει έναν εκδοτικό οίκο.
     * @param entity Ο εκδοτικός οίκος
     */
    public void delete(Publisher entity) {
        entities.remove(entity);
    }

    /**
     * Αποθηκεύει έναν εκδοτικό οίκο.
     * @param entity Ο εκδοτικός οίκος
     */
    public void save(Publisher entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει έναν εκδοτικό οίκο με βάση τον κωδικό του.
     * @param publisherΙd Ο κωδικός του εκδοτικού οίκου
     * @return Ο εκδοτικός οίκος που βρέθηκε ή null
     */
    public Publisher find(int publisherΙd)
    {
        for(Publisher publisher : entities)
            if(publisher.getId() == publisherΙd)
                return publisher;

        return null;
    }

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε έναν εκδοτικό οίκο.
     * @return Ο κωδικός του εκδοτικού οίκου
     */
    @Override
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
