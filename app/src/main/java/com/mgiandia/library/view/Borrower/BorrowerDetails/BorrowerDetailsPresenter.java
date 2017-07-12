package com.mgiandia.library.view.Borrower.BorrowerDetails;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BorrowerDetailsPresenter
{
    private BorrowerDetailsView view;
    private LoanDAO loans;
    private BorrowerDAO borrowers;
    private Borrower attachedBorrower;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param borrowers Ένα instance του borrower
     * @param loans Ένα instance του loan
     */
    public BorrowerDetailsPresenter(BorrowerDetailsView view, BorrowerDAO borrowers, LoanDAO loans)
    {
        this.view = view;
        this.borrowers = borrowers;
        this.loans = loans;

        attachedBorrower = borrowers.find(view.getAttachedBorrowerID());

        view.setPageName("Δανειζόμενος #" + attachedBorrower.getBorrowerNo());

        view.setID("#"+attachedBorrower.getBorrowerNo());
        view.setFirstName(attachedBorrower.getFirstName());
        view.setLastName(attachedBorrower.getLastName());
        view.setCategory(attachedBorrower.getCategory().getDescription());
        view.setPhone(attachedBorrower.getTelephone().getTelephoneNumber());
        view.setEmail(attachedBorrower.getEmail().getAddress());

        view.setCountry(attachedBorrower.getAddress().getCountry());
        view.setAddressCity(attachedBorrower.getAddress().getCity());
        view.setAddressStreet(attachedBorrower.getAddress().getStreet());
        view.setAddressNumber(attachedBorrower.getAddress().getNumber());
        view.setAddressPostalCode(attachedBorrower.getAddress().getZipCode().getCode());
    }

    /**
     * Εμφανίζει το κουμπί της επεξεργασίας του
     * δανιζόμενου.
     */
    public void onStartEditButtonClick()
    {
        view.startEdit(attachedBorrower.getBorrowerNo());
    }

    /**
     * Εμφανίζει το κουμπί της διαγραφής του
     * δανιζόμενου.
     */
    public void onStartDeleteButtonClick()
    {
        view.startDelete("Διαγραφή Δανειζομένου;", "Όλα τα βιβλία που δεν έχουν επιστραφεί θα σημειωθούν ως χαμένα.");
    }

    /**
     * Κατα την διαγραφή του δανιζόμενου
     * το μήνυμα που εμφανίζεται καθώς και
     * η ολοκλήρωση της διαγραφής του.
     */
    void onDoDeleteAndFinish()
    {
        String msg = "Επιτυχής διαγραφή του δανειζόμενου '"+attachedBorrower.getLastName()+" "+ attachedBorrower.getFirstName()+"'!";

        for(Loan loan : attachedBorrower.getLoans())
        {
            loan.getItem().lost();
            loans.delete(loan);
        }

        borrowers.delete(attachedBorrower);
        attachedBorrower = null;

        view.doDeleteAndFinish(msg);
    }

    /**
     * Εμφανίζει ενα μήνυμα με περιεχόμενο value.
     * @param value To περιεχόμενο του μηνύματος
     */
    public void onShowToast(String value)
    {
        view.showToast(value);
    }
}
