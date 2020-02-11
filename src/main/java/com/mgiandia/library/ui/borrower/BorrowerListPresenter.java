package com.mgiandia.library.ui.borrower;

import java.util.List;

import javax.persistence.EntityManager;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.ui.ViewRegistry;

public class BorrowerListPresenter {
    private BorrowerListView view;
    private List<Borrower> borrowers;
    private EntityManager em;
    
    public BorrowerListPresenter(BorrowerListView view) {
        this.view = view;
    }
    
    public void start() {
        view.setPresenter(this);
        em = JPAUtil.createEntityManager();
        getBorrowerList();
        view.open();
    }

	@SuppressWarnings("unchecked")
	private void getBorrowerList() {
		borrowers = em.createQuery("select b from Borrower b").getResultList();
        view.setBorrowers(borrowers);
	}
    
    public List<Borrower> getBorrowers() {
        return borrowers;
    }
    
    
    public void editSelected() {
        BorrowerView borrowerView = ViewRegistry.getBorrowerView();
        BorrowerPresenter borrowerPresenter = new  BorrowerPresenter(borrowerView);
        borrowerPresenter.setBorrower(view.getSelectedBorrower());
        borrowerPresenter.setEntityManager(em);
        borrowerPresenter.start();
    }
    
    public void addBorrower() {
    	BorrowerView borrowerView = ViewRegistry.getBorrowerView();
    	BorrowerPresenter borrowerPresenter = new  BorrowerPresenter(borrowerView);
        borrowerPresenter.setEntityManager(em);
    	borrowerPresenter.setBorrower(new Borrower());
        borrowerPresenter.start();
    }
    
    public void refresh() {
        getBorrowerList();
    }
}
