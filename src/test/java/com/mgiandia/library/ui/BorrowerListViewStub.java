package com.mgiandia.library.ui;

import java.util.List;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;
import com.mgiandia.library.ui.borrower.BorrowerListView;

public class BorrowerListViewStub extends ViewStub implements BorrowerListView{
	private BorrowerListPresenter presenter;
	private List<Borrower> borrowers;
	private Borrower selectedBorrower;
	
	@Override
	public void setPresenter(BorrowerListPresenter presenter) {
		this.presenter = presenter;
	}
	
	public BorrowerListPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}
	
	public List<Borrower> getBorrowers() {
		return borrowers;
	}
	

	@Override
	public Borrower getSelectedBorrower() {
		return selectedBorrower;
	}
	
	public void setSelectedBorrower(Borrower selectedBorrower) {
		this.selectedBorrower = selectedBorrower;
	}

}
