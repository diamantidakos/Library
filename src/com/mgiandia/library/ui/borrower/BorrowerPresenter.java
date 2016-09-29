package com.mgiandia.library.ui.borrower;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mgiandia.library.domain.Borrower;

public class BorrowerPresenter {
    private BorrowerView view;
    private Borrower borrower = new Borrower();
    private EntityManager entityManager;
    
    
    public BorrowerPresenter(BorrowerView view) {
        this.view = view;
    }


    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
        updateView();
    }
    
    public void setEntityManager(EntityManager em) {
    	entityManager = em;
    }


    public Borrower getBorrower() {
        return borrower;
    }
    
    public void start() {
        view.setPresenter(this);
        updateView();
        view.open();
        
    }
    
    
    private void updateView() {
    	view.setBorrowerNo(borrower.getBorrowerNo());
        view.setFirstName(borrower.getFirstName());
        view.setLastName(borrower.getLastName());        
    }
    
    public void save() {
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    
    	borrower.setBorrowerNo(view.getBorrowerNo());
        borrower.setFirstName(view.getFirstName());
        borrower.setLastName(view.getLastName());
        
        entityManager.persist(borrower);
        tx.commit();
        view.close();
    }
    
    public void cancel() {
        view.close();
    }
    
}
