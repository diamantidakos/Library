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

    /**
     * Επιστρέφει τα δεδομένα για μία λίστα από δανειζόμενα βιβλία.
     * @param loans Τα δανειζόμενα βιβλία στα οποία θα επιστραφούν τα δεδομένα
     * @return Μία λίστα με τις λεπτομέρειες των εμφανιζόμενων βιβλίων
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
     * Επιστρέφει τα δανειζόμενα βιβλία για τον δανειζόμενο.
     * @param loans Tα δανειζόμενα βιβλία στα οποία θα
     * επιστραφούν τα δεδομένα
     * @param borrower O δανειζόμενος
     * @return Τα βιβλία του δανειζόμενου
     */
    private ArrayList<Loan> filterLoans(LoanDAO loans, Borrower borrower)
    {
        ArrayList<Loan> tmp = new ArrayList<>();

        for(Loan loan : loans.findAll())
            if(loan.getBorrower().getBorrowerNo() == borrower.getBorrowerNo() && loan.getItem().getState() == ItemState.LOANED)
                tmp.add(loan);

        return tmp;
    }

    /**
     * Αρχικοποιεί τον Presenter έτσι ώστε
     * αργότερα να πραγματοποιηθούν οι επιστροφές.
     * @param view Ένα instance του view
     * @param borrowers Ένα instance του borrower
     * @param loans Ένα instance του loan
     */
    public ManageReturnsPresenter(ManageReturnsView view, LoanDAO loans, BorrowerDAO borrowers)
    {
        this.view = view;
        this.loans = loans;
        borrower = borrowers.find(view.getAttachedBorrowerID());

        view.setPageName("Αντίτυπα προς Επιστροφή");

        onLoadSource();
    }

    /**
     * Πριν πραγματοποιηθεί η τροποποίηση του βιβλίου με id uid
     * εμφανίζεται το παρακάτω μήνυμα.
     * @param uid Το μοναδικό id του βιβλίου
     */
    void onClickItem(int uid)
    {
        Borrower borrower = loans.find(uid).getBorrower();
        view.newLoanStateSelectAlert(uid, "Τροποποίηση κατάστασης αντιτύπου", "Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου; Αυτή τι στιγμή είναι δανεισμένο στον δανειζόμενο '"+borrower.getLastName()+" "+borrower.getFirstName()+"';");
    }

    /**
     * Κατά την αλλαγή της κατάστασης ενός δανειζομένου
     * βιβλίου αλλάζετε η κατάσταση του ως επιστραμένο ή
     * χαμένο και εμφανίζετε ανάλογο μήνυμα.
     * @param uid Το μοναδικό id του βιβλίου
     * @param returnedElseLost Ένα boolean με την κατάσταση του βιβλίου
     */
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

    /**
     * Εμφανίζει ένα μήνυμα alert.
     */
    void onAddNewItem()
    {
        view.showAlert("Επιστροφή Αντιτύπου", "Επιλέξτε ένα αντίτυπο από τη λίστα προκειμένου να το επιστρέψετε ή να το σημειώσετε ως χαμένο.");
    }

    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(filterLoans(loans, borrower)));
    }
}
