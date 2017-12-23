package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerCategoryDAO;
import com.mgiandia.library.domain.BorrowerCategory;

/**
 * @author Νίκος Διαμαντίδης
 */

public class BorrowerCategoryDAOMemory implements BorrowerCategoryDAO
{
    protected static List<BorrowerCategory> entities = new ArrayList<>();

    /**
     * Αποθηκεύει μία κατηγορία δανειζομένου.
     * @param entity Η κατηγορία δανειζομένου
     */
    public void save(BorrowerCategory entity)
    {
        if (! entities.contains(entity))
            entities.add(entity);
    }

    /**
     * Διαγράφει μία κατηγορία δανειζομένου.
     * @param entity Η κατηγορία δανειζομένου
     */
    public void delete(BorrowerCategory entity)
    {
        entities.remove(entity);
    }

    /**
     * Βρίσκει μία κατηγορία δανειζομένου με βάση τον κωδικό του.
     * @param uid Ο κωδικός της κατηγορίας
     * @return Η κατηγορία δανειζομένου
     */
    public BorrowerCategory find(int uid)
    {
        for(BorrowerCategory borrowerCategory : entities) {
            if (borrowerCategory.getId() == uid ) {
                return borrowerCategory;
            }
        }

        return null;
    }

    /**
     * Επιστρέφει όλες τις κατηγορίες δανειζομένων.
     * @return Οι κατηγορίες δανειζομένων
     */
    public List<BorrowerCategory> findAll()
    {
        return new ArrayList(entities);
    }

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία κατηγορία δανειζομένων.
     * @return Ο κωδικός
     */
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
