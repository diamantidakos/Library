package com.mgiandia.library.view.Loans.ManageLoans;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageLoansPresenter
{
    private ManageLoansView view;
    private LoanDAO loans;
    private Borrower borrower;

    /**
     * Επιστρέφει τα δεδομένα για μία λίστα από δάνεια.
     * @param loans Τα δάνεια στα οποίο θα επιστραφούν τα δεδομένα
     * @return Μία λίστα με τις λεπτομέρειες των δανείων
     */
    private List<Quadruple> createDataSource(List<Loan> loans)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Loan loan : loans)
            triplets.add(new Quadruple(loan.getId(),  loan.getBorrower().getLastName()+" "+loan.getBorrower().getFirstName(),
                    loan.getItem().getBook().getTitle(), "Δανεισμός: #"+loan.getId()+". Αντίτυπο: #"+loan.getItem().getItemNumber()));

        return triplets;
    }

    /**
     * Δέχεται μία λίστα με δάνεια και δανειζομένους
     * και επιστρέφει τους δανειζομένους που έχουν
     * αυτά τα δάνεια.
     * @param loans Ένα instance του loan
     * @param borrower Ένα instance του borrower
     * @return Μία λίστα με τους δανειζομένους
     */
    private ArrayList<Loan> filterLoans(LoanDAO loans, Borrower borrower)
    {
        ArrayList<Loan> tmp = new ArrayList<>();

        for(Loan loan : loans.findAll())
            if(loan.getBorrower().getBorrowerNo() == borrower.getBorrowerNo())
                tmp.add(loan);

        return tmp;
    }
    /**
     * Αρχικοποιεί τον Presenter.
     * @param view Ένα instance του view
     * @param loans Ένα instance του loan
     * @param borrowers Ένα instance του borrower
     */
    public ManageLoansPresenter(ManageLoansView view, LoanDAO loans, BorrowerDAO borrowers)
    {
        this.view = view;
        this.loans = loans;
        borrower = borrowers.find(view.getAttachedBorrowerID());

        view.setPageName("Δανεισμοί Αντιτύπων");

        onLoadSource();
    }

    /**
     * Ξεκινάει το activity AddLoansActivity
     */
    void onAddNewItem()
    {
        view.startAddNew(view.getAttachedBorrowerID());
    }

    /**
     * Εμφανίζει ένα μήνυμα μορφής toast
     * @param uid Το μοναδικό id του δανειζόμενου
     */
    void onClickItem(int uid)
    {
        view.showAlert("Τροποποίηση κατάστασης αντιτύπου", "Για να τροποποιήσετε την κατάσταση του αντιτύπου χρησιμοποιήστε την Περίπτωση Χρήσης 'Αντίτυπα'. Για να το επιστρέψετε χρησιμοποιήστε την περίπτωση χρήσης 'Επιστροφές'.");
    }

    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(filterLoans(loans, borrower)));
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }
}
