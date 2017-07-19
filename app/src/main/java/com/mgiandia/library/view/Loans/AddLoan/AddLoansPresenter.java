package com.mgiandia.library.view.Loans.AddLoan;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.util.ItemStateString;


/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddLoansPresenter {

    private AddLoansView view;
    private BookDAO books;
    private LoanDAO loans;
    Borrower attachedBorrower;

    /**
     * Αρχικοποιεί τον Presenter έτσι ώστε
     * αργότερα να πραγματοποιηθούν οι δανεισμοί.
     * @param view Ένα instance του view
     * @param books Ένα instance του book
     * @param borrowers Ένα instance του borrower
     * @param loans Ένα instance του loan
     */
    public AddLoansPresenter(AddLoansView view, BookDAO books, BorrowerDAO borrowers, LoanDAO loans)
    {
        this.view = view;
        this.books = books;
        this.loans = loans;

        attachedBorrower = borrowers.find(view.getAttachedBorrowerID());

        view.setBorrowerId("#"+attachedBorrower.getBorrowerNo());
        view.setPageName("Πραγματοποίηση Δανεισμού");

        List<String> booksNames = new ArrayList<>();

        for(Book book : books.findAll())
            booksNames.add(book.getTitle());

        view.setBookList(booksNames);
    }

    /**
     * Κατά την αποθήκευση γίνεται
     * ενημέρωση κατά πόσον πραγματοποιήθηκε το
     * αντίτυπο ή αν για κάποιο λόγω ο δανεισμός
     * δεν ήταν εφικτός.
     */
    public void onSaveLoan()
    {
        Book selectedBook = books.find(view.getSelectedBookId());

        Item availableItem = null;

        for(Item item : selectedBook.getItems())
            if(item.getState() == ItemState.AVAILABLE)
            {
                availableItem = item;
                break;
            }

        if(availableItem == null)
            view.showAlert("Ανέφικτος ο Δανεισμός", "Το επιλεγμένο βιβλίο δεν έχει αντίτυπα με κατάσταση ("+ ItemStateString.convert(ItemState.AVAILABLE)+").");
        else if(!attachedBorrower.canBorrow())
            view.showAlert("Ανέφικτος ο Δανεισμός", "Ο συγκεκριμένος Δανειζόμενος δεν μπορεί να δανειστεί βιβλία.");
        else
        {
            loans.save(availableItem.borrow(attachedBorrower, loans.nextId()));
            view.successfullyAddLoanAndFinishActivity("Ο Δανειζόμενος #"+attachedBorrower.getBorrowerNo()+" δανείστηκε το αντίτυπο #"+availableItem.getItemNumber());
        }
    }
}
