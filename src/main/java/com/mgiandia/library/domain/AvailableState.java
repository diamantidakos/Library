package com.mgiandia.library.domain;

import com.mgiandia.library.util.SystemDate;

public class AvailableState extends ItemObjectState {

	@Override
	Loan borrow(Item item, Borrower borrower) {
		if (borrower == null) {
            return null;
        }

        if (!borrower.canBorrow()) {
            return null;
        }

        if (getState() != ItemState.AVAILABLE) {
            return null;
        }

        Loan loan = new Loan();
        loan.setItem(item);
        loan.setBorrower(borrower);
        loan.setLoanDate(SystemDate.now());
		item.setObjectState(new LoanedState());
		return loan;
	}
	
	@Override
	void withdraw(Item item) {
		item.setObjectState(new WithdrawnState());
	}
	
    @Override
    ItemState getState() {
        return ItemState.AVAILABLE;
    }

}
