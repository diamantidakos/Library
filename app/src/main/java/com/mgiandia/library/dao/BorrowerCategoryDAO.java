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
     * Αποθηκεύει μία κατηγορία δανειζομένου.
     * @param entity Η κατηγορία δανειζομένου
     */
    void save(BorrowerCategory entity);

    /**
     * Διαγράφει μία κατηγορία δανειζομένου.
     * @param entity Η κατηγορία δανειζομένου
     */
    void delete(BorrowerCategory entity);

    /**
     * Βρίσκει μία κατηγορία δανειζομένου με βάση τον κωδικό του.
     * @param uid Ο κωδικός της κατηγορίας
     * @return Η κατηγορία δανειζομένου
     */
    BorrowerCategory find(int uid);

    /**
     * Επιστρέφει όλες τις κατηγορίες δανειζομένων.
     * @return Οι κατηγορίες δανειζομένων
     */
    List<BorrowerCategory> findAll();

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία κατηγορία δανειζομένων.
     * @return Ο κωδικός
     */
    int nextId();
}
