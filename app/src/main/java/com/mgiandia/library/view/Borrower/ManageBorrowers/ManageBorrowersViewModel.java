package com.mgiandia.library.view.Borrower.ManageBorrowers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ManageBorrowersViewModel extends ViewModel implements ButtonClicked
{
    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();
    private ManageBorrowersPresenter presenter;
    private MutableLiveData<Integer> selectedBorrowerID = new MutableLiveData<>();
    private MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Borrower>> borrowers = new MutableLiveData<>();

    public ManageBorrowersPresenter getPresenter(ManageBorrowersView view)
    {
        presenter = new ManageBorrowersPresenter(view, borrowerDAO);
        return presenter;
    }

    public List<Borrower> getAllBorrowers()
    {
        return borrowerDAO.findAll();
    }

    public Set<Borrower> findBorrowers(String name)
    {
        return borrowerDAO.findByName(name);
    }

    public void setSelectedBorrowerID(int selectedBorrowerID)
    {
        this.selectedBorrowerID.setValue(selectedBorrowerID);
    }

    public MutableLiveData<Integer> getSelectedBorrowerID()
    {
        return selectedBorrowerID;
    }

    public void setTextOnSearchBar(String textOnSearchBar)
    {
        this.textOnSearchBar.setValue(textOnSearchBar);
    }

    public MutableLiveData<String> getTextOnSearchBar()
    {
        return textOnSearchBar;
    }

    public void setBorrowers(ArrayList<Borrower> borrowers)
    {
        this.borrowers.setValue(borrowers);
    }

    public MutableLiveData<ArrayList<Borrower>> getBorrowers()
    {
        return borrowers;
    }
}
