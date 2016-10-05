package com.mgiandia.library.ui;

import com.mgiandia.library.ui.borrower.BorrowerJFrame;
import com.mgiandia.library.ui.borrower.BorrowerListJFrame;
import com.mgiandia.library.ui.borrower.BorrowerListView;
import com.mgiandia.library.ui.borrower.BorrowerView;

public class ViewRegistry {
	private static BorrowerListView borrowerListViewStub;
	private static BorrowerView borrowerViewStub;
	
	
	public static void setBorrowerListView(BorrowerListView borrowerListView) {
		borrowerListViewStub = borrowerListView;
	}
	
	public static void setBorrowerView(BorrowerView borrowerView) {
		borrowerViewStub = borrowerView;
	}
	
    public static BorrowerListView getBorrowerListView() {
        return borrowerListViewStub == null ? new BorrowerListJFrame() 
        	: borrowerListViewStub ;
    }
    
    public static BorrowerView getBorrowerView() {
    	return borrowerViewStub == null ? new BorrowerJFrame() 
    		: borrowerViewStub;
    }

    public static void reset() {
    	borrowerListViewStub = null;
    }
}
