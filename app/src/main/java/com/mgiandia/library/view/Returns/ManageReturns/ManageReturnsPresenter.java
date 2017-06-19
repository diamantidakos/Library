package com.mgiandia.library.view.Returns.ManageReturns;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageReturnsPresenter
{
    private ManageReturnsView view;
    private LoanDAO loans;
    private Borrower borrower;

    private List<Quadruple> createDataSource(List<Loan> loans)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Loan loan : loans)
            triplets.add(new Quadruple(loan.getId(),  loan.getBorrower().getLastName()+" "+loan.getBorrower().getFirstName(),
                    loan.getItem().getBook().getTitle(), "Δανεισμός: #"+loan.getId()+". Αντίτυπο: #"+loan.getItem().getItemNumber()));

        return triplets;
    }

    private ArrayList<Loan> filterLoans(LoanDAO loans, Borrower borrower)
    {
        ArrayList<Loan> tmp = new ArrayList<>();

        for(Loan loan : loans.findAll())
            if(loan.getBorrower().getBorrowerNo() == borrower.getBorrowerNo() && loan.getItem().getState() == ItemState.LOANED)
                tmp.add(loan);

        return tmp;
    }

    public ManageReturnsPresenter(ManageReturnsView view, LoanDAO loans, BorrowerDAO borrowers)
    {
        this.view = view;
        this.loans = loans;
        borrower = borrowers.find(view.getAttachedBorrowerID());

        view.setPageName("Αντίτυπα προς Επιστροφή");

        onLoadSource();
    }

    void onClickItem(int uid)
    {
        Borrower borrower = loans.find(uid).getBorrower();
        view.newLoanStateSelectAlert(uid, "Τροποποίηση κατάστασης αντιτύπου", "Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου; Αυτή τι στιγμή είναι δανεισμένο στον δανειζόμενο '"+borrower.getLastName()+" "+borrower.getFirstName()+"';");
    }

    void onChangeItemState(int uid, boolean returnedElseLost)
    {
        Loan loan = loans.find(uid);

        if(returnedElseLost)
        {
            loan.returnItem();

            view.refresh();
            view.showToast("Το αντίτυπο επιστράφηκε!");
        }
        else
        {
            loan.getItem().lost();
            loans.delete(loan);

            view.refresh();
            view.showToast("Το αντίτυπο σημειώθηκε ως χαμένο!");
        }
    }

    void onAddNewItem()
    {
        view.showAlert("Επιστροφή Αντιτύπου", "Επιλέξτε ένα αντίτυπο από τη λίστα προκειμένου να το επιστρέψετε ή να το σημειώσετε ως χαμένο.");
    }

    void onLoadSource()
    {
        view.loadSource(createDataSource(filterLoans(loans, borrower)));
    }
}
