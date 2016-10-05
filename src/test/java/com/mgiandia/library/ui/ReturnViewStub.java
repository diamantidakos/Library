package com.mgiandia.library.ui;

import com.mgiandia.library.ui.loan.ReturnPresenter;
import com.mgiandia.library.ui.loan.ReturnView;

public class ReturnViewStub extends ViewStub implements ReturnView {
	private ReturnPresenter presenter;
	private int itemNumber; 
	
	@Override
	public void setPresenter(ReturnPresenter presenter) {
		this.presenter = presenter; 
	}

	public ReturnPresenter getPresenter() {
		return presenter;
	}
	
	@Override
	public int getItemNumber() {
		return itemNumber;
	}
	
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}
