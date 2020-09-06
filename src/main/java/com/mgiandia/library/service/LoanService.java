package com.mgiandia.library.service;



import java.time.LocalDate;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;

/**
 * Η υπηρεσία του δανεισμού. Αναλαμβάνει την αναζήτηση
 * δανειζομένων και αντιτύπων και καταγράφει τους δανεισμούς
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanService {
    private Borrower borrower;

    /**
     * Αναζητά το δανειζόμενο με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου
     * @return {@code true} αν βρεθεί ο δανειζόμενος
     */
    public Boolean findBorrower(int borrowerNo) {
        BorrowerDAO borrowerDao = new BorrowerDAOMemory();
        borrower = borrowerDao.find(borrowerNo);
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
    public LocalDate borrow(int itemNo) {
        if (borrower == null) {
            throw new LibraryException();
        }
        if (!borrower.canBorrow()) {
            return null;
        }
        ItemDAO itemDao = new ItemDAOMemory();
        Item item = itemDao.find(itemNo);
        Loan loan = item.borrow(borrower);
        LoanDAO loanDao = new LoanDAOMemory();
        loanDao.save(loan);
        return loan.getDue();
    }

}

