package com.mgiandia.library.service;

import java.util.List;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.EmailMessage;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;

/**
 * Η υπηρεσία που ενημερώνει με μήνυμα ηλεκτρονικού
 * ταχυδρομείου όσους δανειζομένους
 * έχουν καθυστερήσει την επιστροφή κάποιων
 * αντιτύπων.
 * @author Νίκος Διαμαντίδης
 *
 */
public class NotificationService {
    private EmailProvider provider;

    /**
     * Θέτει τον πάροχο του ηλεκτρονικού ταχυδρομείου.
     * @param provider Ο πάροχος ηλεκτρονικού ταχυδρομείου.
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }

    /**
     * Ενημερώνει όσους δανειζομένους.
     * έχουν καθυστερήσει της επιστροφή.
     * κάποιου αντιτύπου.
     */
    public void notifyBorrowers() {
        if (provider == null) {
            throw new LibraryException();
        }

        LoanDAO loanDao = DAOFactory.getFactory().getLoanDAO();
        List<Loan> allLoans = loanDao.findAllPending();
        for (Loan loan : allLoans) {
            if (loan.isOverdue() && loan.getBorrower().getEmail()!=null &&
            		loan.getBorrower().getEmail().isValid()) {
                String message = composeMessage(loan.getItem().getBook(),
                        -loan.daysToDue());
                sendEmail(loan.getBorrower(),
                        "Καθυστέρηση Αντιτύπου", message);
            }
        }
    }


    private void sendEmail(Borrower borrower,
            String subject, String message) {
        EmailAddress eMail = borrower.getEmail();
        if (eMail == null || !eMail.isValid()) {
            return;
        }
        
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(eMail);
        emailMessage.setSubject(subject);
        emailMessage.setBody(message);
        provider.sendEmail(emailMessage);
    }
    
    private String composeMessage(Book book, long overdue) {
        String message = "Έχετε καθυστερήσει το βιβλίο με Τίτλο ";
        message += book.getTitle();
        message += " για ";
        message += overdue;
        message += " ημέρες ";
        return message;
    }
}
