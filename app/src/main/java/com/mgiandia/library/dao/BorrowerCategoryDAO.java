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
    /**
     * Αποθηκεύει μία κατηγορία δανιζομένου.
     * @param entity Η κατηγορία δανιζομένου
     */
    void save(BorrowerCategory entity);

    /**
     * Διαγράφει μία κατηγορία δανιζομένου.
     * @param entity Η κατηγορία δανιζομένου
     */
    void delete(BorrowerCategory entity);

    /**
     * Βρίσκει μία κατηγορία δανιζομένου με βάση τον κωδικό του.
     * @param uid Ο κωδικός της κατηγορίας
     * @return Η κατηγορία δανιζομένου
     */
    BorrowerCategory find(int uid);

    /**
     * Επιστρέφει όλες τις κατηγορίες δανιζομένων.
     * @return Οι κατηγορίες δανιζομένων
     */
    List<BorrowerCategory> findAll();

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε μία κατηγορία δανιζομένων.
     * @return Ο κωδικός
     */
    int nextId();
}
