package com.mgiandia.library.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.util.Money;

/**
 * Η υπηρεσία επιστροφής αντιτύπου.
 * @author Νίκος Διαμαντίδης
 *
 */
public class ReturnService {

    /**
     * Πραγματοποιεί την επιστροφή ενός αντιτύπου. 
     * Επιστρέφει το τυχόν πρόστιμο που πρέπει να καταβληθεί.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου που επιστρέφεται.
     * @return Το πρόστιμο που πρέπει να πληρωθεί ή {@code null}
     * αν δεν υπάρχει πρόστιμο.
     */
    public Money returnItem(int itemNo) {
        Money fine = null;
        String jpql = "select l from Loan l where l.item.itemNumber = :itemid and l.returnDate is null";
        
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
            Query query = em.createQuery(jpql).setParameter("itemid", itemNo);
            Loan loan = (Loan) query.getSingleResult();       
            loan.returnItem();
            if (loan.getOverdue() > 0) {
                fine = loan.getFine();
            }
            
            tx.commit();
            em.close();
        	
        } catch (Exception ex) {
        	tx.rollback();
        	em.close();
        	throw new LibraryException();
        }
        
        return fine;
    }
}
