package com.mgiandia.library.view.Returns.ManageReturns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ManageReturnsViewModel extends ViewModel implements ButtonClicked
{
    private ManageReturnsPresenter presenter;
    private LoanDAO loanDAO = new LoanDAOMemory();
    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();
    private MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();
    private MutableLiveData<Integer> borrowerID = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedLoanID = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Loan>> loans = new MutableLiveData<>();

    public ManageReturnsPresenter getPresenter(ManageReturnsView view)
    {
        presenter = new ManageReturnsPresenter(view, loanDAO, borrowerDAO);
        return presenter;
    }

    public List<Loan> getAllLoans(int borrowerID)
    {
        return loanDAO.findBorrowersLoans(borrowerID);
    }

    public Set<Loan> findLoansByBookTitle(String title)
    {
        Set<Loan> loans = loanDAO.findByBookTitle(title);
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext())
        {
            Loan loan = iterator.next();
            if (loan.getBorrower().getBorrowerNo() != borrowerID.getValue())
            {
                iterator.remove();
            }
        }

        return loans;
    }

    public void setTextOnSearchBar(String textOnSearchBar)
    {
        this.textOnSearchBar.setValue(textOnSearchBar);
    }

    public MutableLiveData<String> getTextOnSearchBar()
    {
        return textOnSearchBar;
    }

    public void setBorrowerID(int borrowerID)
    {
        this.borrowerID.setValue(borrowerID);
    }

    public MutableLiveData<Integer> getBorrowerID()
    {
        return borrowerID;
    }

    public void setSelectedLoanID(int selectedLoanID)
    {
        this.selectedLoanID.setValue(selectedLoanID);
    }

    public MutableLiveData<Integer> getSelectedLoanID()
    {
        return selectedLoanID;
    }

    public void setLoans(ArrayList<Loan> loans)
    {
        this.loans.setValue(loans);
    }

    public MutableLiveData<ArrayList<Loan>> getLoans()
    {
        return loans;
    }
}