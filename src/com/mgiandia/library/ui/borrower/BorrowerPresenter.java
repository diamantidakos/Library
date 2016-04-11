package com.mgiandia.library.ui.borrower;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.domain.Borrower;

public class BorrowerPresenter {
    private BorrowerView view;
    private Borrower borrower = new Borrower();
    private BorrowerDAO borrowerDao;
    
    public BorrowerPresenter(BorrowerView view) {
        this.view = view;
        borrowerDao = DAOFactory.getFactory().getBorrowerDAO();
    }


    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
        updateView();
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
    	borrower.setBorrowerNo(view.getBorrowerNo());
        borrower.setFirstName(view.getFirstName());
        borrower.setLastName(view.getLastName());
        borrowerDao.save(borrower);
        view.close();
    }
    
    public void cancel() {
        view.close();
    }
    
}
