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
     * Αποθηκεύει μία κατηγορία δανιζομένου.
     * @param entity Η κατηγορία δανιζομένου
     */
    public void save(BorrowerCategory entity)
    {
        if (! entities.contains(entity))
            entities.add(entity);
    }

    /**
     * Διαγράφει μία κατηγορία δανιζομένου.
     * @param entity Η κατηγορία δανιζομένου
     */
    public void delete(BorrowerCategory entity)
    {
        entities.remove(entity);
    }

    /**
     * Βρίσκει μία κατηγορία δανιζομένου με βάση τον κωδικό του.
     * @param uid Ο κωδικός της κατηγορίας
     * @return Η κατηγορία δανιζομένου
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
     * Επιστρέφει όλες τις κατηγορίες δανιζομένων.
     * @return Οι κατηγορίες δανιζομένων
     */
    public List<BorrowerCategory> findAll()
    {
        return new ArrayList(entities);
    }

    /**
     * Επιστρέφει τον επώμενο κωδικό που μπορέι να αποδοθεί σε μία κατηγορία δανιζομένων.
     * @return Ο κωδικός
     */
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
