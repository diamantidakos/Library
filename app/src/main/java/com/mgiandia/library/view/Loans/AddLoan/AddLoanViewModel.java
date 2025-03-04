package com.mgiandia.library.view.Loans.AddLoan;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddLoanViewModel extends ViewModel implements ButtonClicked
{
    private AddLoansPresenter presenter;
    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();

    public AddLoansPresenter getPresenter(AddLoansView view)
    {
        presenter = new AddLoansPresenter(view, new BookDAOMemory(), borrowerDAO, new LoanDAOMemory());
        return presenter;
    }

    public Borrower findBorrower(int borrowerID)
    {
        return borrowerDAO.find(borrowerID);
    }

    private final MutableLiveData<String> borrower = new MutableLiveData<>();
    //private final MutableLiveData<String> selectedBook = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedBookID = new MutableLiveData<>();

    public void setBorrowerFullName(String borrower)
    {
        this.borrower.setValue(borrower);
    }

    public MutableLiveData<String> getBorrowerFullName()
    {
        return borrower;
    }

    public void setSelectedBookID(int bookID)
    {
        this.selectedBookID.setValue(bookID);
    }

    public MutableLiveData<Integer> getSelectedBookID()
    {
        return selectedBookID;
    }
}