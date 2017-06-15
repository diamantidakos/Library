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

    public void onStartEditButtonClick()
    {
        view.startEdit(attachedBorrower.getBorrowerNo());
    }

    public void onStartDeleteButtonClick()
    {
        view.startDelete("Διαγραφή Δανειζομένου;", "Όλα τα βιβλία που δεν έχουν επιστραφεί θα σημειωθούν ως χαμένα.");
    }

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

    public void onShowToast(String value)
    {
        view.showToast(value);
    }
}
