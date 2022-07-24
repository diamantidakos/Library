package com.mgiandia.library.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Money;

/**
 * Η υπηρεσία επιστροφής αντιτύπου.
 * @author Νίκος Διαμαντίδης
 *
 */
@RequestScoped
public class ReturnService {

	@Inject
	EntityManager em;
	
	
    /**
     * Πραγματοποιεί την επιστροφή ενός αντιτύπου. 
     * Επιστρέφει το τυχόν πρόστιμο που πρέπει να καταβληθεί.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου που επιστρέφεται.
     * @return Το πρόστιμο που πρέπει να πληρωθεί ή {@code null}
     * αν δεν υπάρχει πρόστιμο.
     */
	@Transactional
    public Money returnItem(int itemNo) {
        Money fine = null;
        String jpql = "select l from Loan l where l.item.itemNumber = :itemid and l.returnDate is null";
        
        try {
            Query query = em.createQuery(jpql).setParameter("itemid", itemNo);
            Loan loan = (Loan) query.getSingleResult();       
            loan.returnItem();
            if (loan.getOverdue() > 0) {
                fine = loan.getFine();
            }
            
        	
        } catch (Exception ex) {
        	throw new LibraryException();
        }
        
        return fine;
    }
}
