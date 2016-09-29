package com.mgiandia.library.service;



import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.util.SimpleCalendar;

/**
 * Η υπηρεσία του δανεισμού. 
 * Αναλαμβάνει την αναζήτηση
 * δανειζομένων και αντιτύπων και καταγράφει τους δανεισμούς
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanService {
    private Borrower borrower;
    private EntityManager em;
    
    public LoanService() {
    	em = JPAUtil.createEntityManager(); 
    }
    
    /**
     * Αναζητά το δανειζόμενο με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου
     * @return {@code true} αν βρεθεί ο δανειζόμενος
     */
    public Boolean findBorrower(int borrowerNo) {
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
        try {
        	borrower = em.find(Borrower.class, borrowerNo);
        	tx.commit();
        } catch (NoResultException ex) {
        	borrower = null;
        	tx.rollback();
        }

        return borrower != null;
    }

    /**
     * Πραγματοποιεί το δανεισμό.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου
     * @return Την προθεσμία επιστροφής.
     * Επιστρέφει {@code null} εάν ο δανειζόμενος δεν δικαιούται
     * να δανειστεί αντίτυπο.
     * @throws LibraryException Εάν δεν υπάρχει δανειζόμενος
     */
    public SimpleCalendar borrow(int itemNo) {
        if (borrower == null) {
            throw new LibraryException();
        }
        if (!borrower.canBorrow()) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Item item = em.find(Item.class, itemNo);
        Loan loan = item.borrow(borrower);

        em.persist(loan);
        tx.commit();
        return loan.getDue();

    }

}

