package com.mgiandia.library.memorydao;


import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Loan;

/**
 * Η υλοποίηση των αντικείμενων πρόσβασης δεδομένων (DAO)
 * για την κλάση {@link Loan} όπου η εξωτερική πηγή δεδομένων
 * είναι η μνήμη.
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanDAOMemory implements LoanDAO{

    protected static List<Loan> entities = new ArrayList<Loan>();

    public Loan find(int uid)
    {
        for(Loan loan : entities)
            if(loan.getId() == uid)
                return loan;

        return null;
    }

    public void delete(Loan entity) {
        entities.remove(entity);    
    }

    public List<Loan> findAll() {
        return new ArrayList<Loan>(entities);
    }


    public void save(Loan entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    
    public Loan findPending(int itemNo) {
        for(Loan loan : entities) {
            if (loan.getItem().getItemNumber() == itemNo &&
                    loan.isPending()) {
                return loan;
            }
        }
        return null;
    }

    public List<Loan> findAllPending() {
        List<Loan> allLoans = findAll();
        List<Loan> pending = new ArrayList<Loan>();
        
        for(Loan loan : allLoans) {
            if (loan.isPending()) {
                pending.add(loan);
            }
        }
        
        return pending;
    }

    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}