package com.mgiandia.library.ui;

import com.mgiandia.library.ui.loan.LoanPresenter;
import com.mgiandia.library.ui.loan.LoanView;

public class LoanViewStub extends ViewStub implements LoanView {
    private int borrowerNo;
    private int itemNo;
    private boolean loanActionEnabled;
    private String bookTitle;
    private String borrowerFirstName;
    private String borrowerLastName; 
    private LoanPresenter presenter;
    

    public int getBorrowerNo() {
        return borrowerNo;
    }

    public void setBorrowerNo(int borrowerNo) {
        this.borrowerNo = borrowerNo; 
    }
    

    public int getItemNumber() {
        return itemNo;
    }
    
    
    public void setItemNumber(int itemNo) {
        this.itemNo = itemNo; 
    }


    public boolean isLoanActionEnabled() {
        return loanActionEnabled;
    }
    

    public void setBookTitle(String name) {
        bookTitle = name;
        
    }
    
    
    public String getBookTitle() {
        return bookTitle;
    }


    public void setBorrowerFirstName(String name) {
        borrowerFirstName = name;
    }

    public String getBorrowerFirstName() {
        return borrowerFirstName;
    }

    
    public String getBorrowerLastName() {
        return borrowerLastName;
    }
    
    

    public void setBorrowerLastName(String name) {
        borrowerLastName = name; 
        
    }

    

    public void setLoanActionEnabled(boolean enabled) {
        this.loanActionEnabled = enabled;        
    }


    public void setPresenter(LoanPresenter presenter) {
        this.presenter = presenter;
    }

    public LoanPresenter getPresenter() {
        return presenter;
    }
    
}
