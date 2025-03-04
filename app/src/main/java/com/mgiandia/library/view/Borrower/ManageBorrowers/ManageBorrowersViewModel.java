package com.mgiandia.library.view.Borrower.ManageBorrowers;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.List;

public class ManageBorrowersViewModel extends ViewModel implements ButtonClicked
{
    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();
    private ManageBorrowersPresenter presenter;

    public ManageBorrowersPresenter getPresenter(ManageBorrowersView view)
    {
        presenter = new ManageBorrowersPresenter(view, borrowerDAO);
        return presenter;
    }

    public List<Borrower> getAllBorrowers()
    {
        return borrowerDAO.findAll();
    }
}
