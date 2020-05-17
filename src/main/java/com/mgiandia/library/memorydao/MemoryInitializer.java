package com.mgiandia.library.memorydao;

import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;

public class MemoryInitializer extends Initializer {

    @Override
    protected void eraseData() {
                
        List<Borrower> allBorrowers = getBorrowerDAO().findAll();
        for(Borrower borrower : allBorrowers) {
            getBorrowerDAO().delete(borrower);
        }
            
        List<Item> allItems = getItemDAO().findAll();        
        for(Item item : allItems) {
            getItemDAO().delete(item);
        }
        
        List<Loan> allLoans = getLoanDAO().findAll(); 
        for(Loan loan : allLoans) {
            getLoanDAO().delete(loan);
        }    
    }

	@Override
	protected BorrowerDAO getBorrowerDAO() {
		return new BorrowerDAOMemory();
	}

	@Override
	protected ItemDAO getItemDAO() {
		return new ItemDAOMemory();
	}

	@Override
	protected LoanDAO getLoanDAO() {
		return new LoanDAOMemory();
	}
    
}
