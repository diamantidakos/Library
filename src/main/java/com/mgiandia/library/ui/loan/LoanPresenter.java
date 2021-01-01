package com.mgiandia.library.ui.loan;

import java.time.format.DateTimeFormatter;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;

/**
 * Ο presenter του δανεισμού 
 * @author Νίκος Διαμαντίδης 
 *
 */
public class LoanPresenter {
    private LoanView view;
    private boolean borrowerFound;
    private boolean itemFound;
    private Borrower borrower;
    private Item item;
    
    private BorrowerDAO borrowerDao;
    private ItemDAO itemDao;
    private LoanDAO loanDao;
    
    /**
     * Κατασκευαστής που δέχεται την όψη την οποία και χειρίζεται
     * @param view Η όψη
     */
    public LoanPresenter(LoanView view) {
        this.view = view;       
        borrowerDao = DAOFactory.getFactory().getBorrowerDAO();
        itemDao = DAOFactory.getFactory().getItemDAO();
        loanDao = DAOFactory.getFactory().getLoanDAO();
    }
   

    /**
     * Επιστρέφει τον τρέχοντα δανειζόμενο
     * @return Ο τρέχων δανειζόμενος
     */
    public Borrower getBorrower() {
        return borrower;
    }
    
    /**
     * Επιστρέφει το τρέχον αντίτυπο
     * @return Το τρέχον αντίτυπο
     */
    public Item getItem() {
        return item;
    }
    
    /**
     * Εκκινεί τον presenter
     */
    public void start() {
        view.setPresenter(this);
        view.open();     
        view.setLoanActionEnabled(false);
    }
    
    /**
     * Ακυρώνει τη διαδικασία
     */
    public void cancel() {
        view.close();
    }
    
    
    /**
     * Ελέγχει για το αν βρέθηκε ο δανειζόμενος
     * @return true εάν έχει βρεθεί ο δανειζόμενος
     */
    public boolean isBorrowerFound() {
        return borrowerFound;
    }
    
    /**
     * Ελέγχει για το αν έχει βρεθεί το αντίτυπο
     * @return true εάν έχει βρεθεί το αντίτυπο
     */
    public boolean isItemFound() {
        return itemFound;
    }
    
    
    /**
     * Αναζητεί το δανειζόμενο
     */
    public void findBorrower() {
        borrower = borrowerDao.find(view.getBorrowerNo());
        if (borrower == null) {
            view.showError("Borrower not found");
            showBorrower("","");            
            borrowerFound = false;            
        } else {
            showBorrower(borrower.getLastName(), borrower.getFirstName());
            borrowerFound = true;            
        }
        checkForLoanAction();
    }
    
    
    /**
     * Αναζητεί το αντίτυπο
     */
    public void findItem() {
        item = itemDao.find(view.getItemNumber()); 
        if (item == null ) {
            view.showError("Item not found");
            view.setBookTitle("");
            itemFound = false;
        } else {
            view.setBookTitle(item.getBook().getTitle());
            itemFound = true;
        }
        checkForLoanAction();
    }
    
    private void showBorrower(String lastName, String firstName) {
        view.setBorrowerLastName(lastName);
        view.setBorrowerFirstName(firstName);
    }

    
    private void checkForLoanAction() {
        if (borrowerFound && itemFound) {
            view.setLoanActionEnabled(true);
        } else {
            view.setLoanActionEnabled(false);
        }        
    }
    
    
    /**
     * Πραγματοποιεί το δανεισμό
     */
    public void borrowItem() {
        if (borrower.canBorrow()) {
            Loan loan = item.borrow(borrower);
            loanDao.save(loan);
            view.showInfo("Due Date " +  loan.getDue().format(DateTimeFormatter.ISO_DATE));
        }
    }
}