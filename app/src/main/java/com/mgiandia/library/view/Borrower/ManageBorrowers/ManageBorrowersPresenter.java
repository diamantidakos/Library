package com.mgiandia.library.view.Borrower.ManageBorrowers;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageBorrowersPresenter
{
    private ManageBorrowersView view;
    private BorrowerDAO borrowers;

    private List<Quadruple> createDataSource(List<Borrower> borrowers)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Borrower borrower : borrowers)
            triplets.add(new Quadruple(borrower.getBorrowerNo(), borrower.getFirstName(), borrower.getLastName(), "Κωδικός: "+borrower.getBorrowerNo()+". Σύνολο δανισμών "+borrower.getLoans().size()));

        return triplets;
    }

    public ManageBorrowersPresenter(ManageBorrowersView view, BorrowerDAO borrowers)
    {
        this.view = view;
        this.borrowers = borrowers;

        onLoadSource();
    }

    void onClickItem(int uid)
    {
        if(view.shouldLoadLoansOnClick())
            view.clickItemLoans(uid);
        else if(view.shouldLoadReturnsOnClick())
            view.clickItemReturns(uid);
        else
            view.clickItem(uid);
    }

    void onStartAddNew()
    {
        view.startAddNew();
    }

    void onShowToast(String value)
    {
        view.showToast(value);
    }

    void onLoadSource()
    {
        view.loadSource(createDataSource(borrowers.findAll()));
    }
}
