package com.mgiandia.library.ui.borrower;

import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.ui.ViewRegistry;

public class BorrowerListPresenter {
    private BorrowerListView view;
    private List<Borrower> borrowers;
    private BorrowerDAO borrowerDao;
    
    public BorrowerListPresenter(BorrowerListView view) {
        this.view = view;
        borrowerDao = new BorrowerDAOMemory();
    }
    
    public void start() {
        view.setPresenter(this);
        getBorrowerList();
        view.open();
    }

	private void getBorrowerList() {
		borrowers = borrowerDao.findAll();
        view.setBorrowers(borrowers);
	}
    
    public List<Borrower> getBorrowers() {
        return borrowers;
    }
    
    
    public void editSelected() {
        BorrowerView borrowerView = ViewRegistry.getBorrowerView();
        BorrowerPresenter borrowerPresenter = new  BorrowerPresenter(borrowerView);
        borrowerPresenter.setBorrower(view.getSelectedBorrower());
        borrowerPresenter.start();
    }
    
    public void addBorrower() {
    	BorrowerView borrowerView = ViewRegistry.getBorrowerView();
    	BorrowerPresenter borrowerPresenter = new  BorrowerPresenter(borrowerView);
        borrowerPresenter.setBorrower(new Borrower());
        borrowerPresenter.start();
    }
    
    public void refresh() {
        getBorrowerList();
    }
}
