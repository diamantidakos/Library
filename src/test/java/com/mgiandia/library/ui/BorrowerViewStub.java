package com.mgiandia.library.ui;

import com.mgiandia.library.ui.borrower.BorrowerPresenter;
import com.mgiandia.library.ui.borrower.BorrowerView;

public class BorrowerViewStub extends ViewStub implements BorrowerView{
	private BorrowerPresenter presenter;
	private int borrowerNo;
	private String firstName;
	private String lastName;
	
	@Override
	public void setPresenter(BorrowerPresenter presenter) {
		this.presenter = presenter;
	}

	public BorrowerPresenter getPresenter() {
		return presenter;
	}
	
	@Override
	public int getBorrowerNo() {
		return borrowerNo;
	}

	@Override
	public void setBorrowerNo(int borrowerNo) {
		this.borrowerNo = borrowerNo;		
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
