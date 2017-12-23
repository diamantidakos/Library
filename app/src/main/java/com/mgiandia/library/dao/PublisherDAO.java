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
    /**
     * Βρίσκει έναν εκδοτικό οίκο με βάση τον κωδικό του.
     * @param publisherΙd Ο κωδικός του εκδοτικού οίκου
     * @return Ο εκδοτικός οίκος που βρέθηκε ή null
     */
    Publisher find(int publisherΙd);


    /**
     * Επιστρέφει όλους τους εκδοτικούς οίκους.
     * @return Οι εκδοτικοί οίκοι
     */
    List<Publisher> findAll();

    /**
     * Αποθηκεύει έναν εκδοτικό οίκο.
     * @param entity Ο εκδοτικός οίκος
     */
    void save(Publisher entity);

    /**
     * Διαγράφει έναν εκδοτικό οίκο.
     * @param entity Ο εκδοτικός οίκος
     */
    void delete(Publisher entity);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε έναν εκδοτικό οίκο.
     * @return Ο κωδικός του εκδοτικού οίκου
     */
    int nextId();
}
