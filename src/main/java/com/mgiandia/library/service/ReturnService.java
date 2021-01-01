package com.mgiandia.library.service;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.dao.*;

/**
 * Η υπηρεσία επιστροφής αντιτύπου.
 * @author Νίκος Διαμαντίδης
 *
 */
public class ReturnService {

    /**
     * Πραγματοποιεί την επιστροφή ενός αντιτύπου και
     * επιστρέφει το τυχόν πρόστιμο που πρέπει να καταβληθεί.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου που επιστρέφεται.
     * @return Το πρόστιμο που πρέπει να πληρωθεί ή {@code null}
     * αν δεν υπάρχει πρόστιμο.
     */
    public Money returnItem(int itemNo) {
    	LoanDAO loanDAO = DAOFactory.getFactory().getLoanDAO();
        Money fine = null;

        Loan loan = loanDAO.findPending(itemNo);
        if (loan == null) {
            throw new LibraryException();
        }

        loan.returnItem();
        if (loan.getOverdue() > 0) {
            fine = loan.getFine();
        }

        loanDAO.save(loan);
        return fine;
    }
}
