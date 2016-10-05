package com.mgiandia.library.ui.loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.util.Money;

/**
 * Ο presenter της επιστροφής αντιτύπου.
 * @author Νίκος Διαμαντίδης
 *
 */
public class ReturnPresenter {
    private ReturnView view;
    private boolean loanFound;
    private Loan loan;
    private EntityManager em;
    
    /**
     * Κατασκευαστής που δέχεται την όψη ως παράμετρο
     * @param view Η όψη που χειρίζεται 
     */
    public ReturnPresenter(ReturnView view) {
        this.view = view;
    }
    
    /**
     * Επιστρέφει true εάν βρέθηκε ο δανεισμός
     * @return true αν βρέθηκε ο δανεισμός
     */
    public boolean isLoanFound() {
        return loanFound;
    }
    
    /**
     * Επιστρέφει τον τρέχοντα δανεισμό
     * @return ο δανεισμός
     */
    public Loan getLoan() {
        return loan;
    }
    
    /**
     * Εκκινεί τον presenter
     */
    public void start() {
    	em = JPAUtil.createEntityManager();
        view.setPresenter(this);
        view.open();       
    }
    
    /**
     * Ακύρωση διαδικασίας
     */
    public void cancel() {
        view.close();
    }
    
    /**
     * Πραγματοποιεί την επιστροφή και εμφανίζει το πρόστιμο αν υπάρχει
     */
    public void returnItem() {
        String jpql = "select l from Loan l where l.item.itemNumber = :itemid and l.returnDate is null";
        Query query = em.createQuery(jpql)
        	.setParameter("itemid", view.getItemNumber());
        
        try {
        	loan = (Loan) query.getSingleResult();
        } catch (NoResultException ex) {
        	loan = null;
        }
        
        if (loan==null) {
            loanFound = false;
            view.showError("Loan not found");
        } else {
        	EntityTransaction tx = em.getTransaction();
        	tx.begin();
            loanFound = true;
            loan.returnItem();
            tx.commit();
            
            if (loan.getOverdue() > 0 ) {
                Money fine = loan.getFine();
                view.showInfo("Fine is " + fine.getAmount() 
                        + " " + fine.getCurrency());                
            } else {
                view.showInfo("Item returned");
            }
        }
    }
    
}
