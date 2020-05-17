package com.mgiandia.library.ui.loan;

import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.LoanDAOMemory;
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
    private LoanDAO loanDao;
    
    /**
     * Κατασκευαστής που δέχεται την όψη ως παράμετρο
     * @param view Η όψη που χειρίζεται 
     */
    public ReturnPresenter(ReturnView view) {
        this.view = view;
        loanDao = new LoanDAOMemory();
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
        loan = loanDao.findPending(view.getItemNumber());
        
        if (loan==null) {
            loanFound = false;
            view.showError("Loan not found");
        } else {
            loanFound = true;
            loan.returnItem();
            
            if (loan.getOverdue() > 0 ) {
                Money fine = loan.getFine();
                view.showInfo("Fine is " + fine.getAmount() 
                        + " " + fine.getCurrency());                
            } else {
                view.showInfo("Item returned");
            }
            
            loanDao.save(loan);
        }
    }
    
}
