package com.mgiandia.library.soap;

public class LoanNotFoundException extends Exception {

    private static final long serialVersionUID = -3873803017071261156L;

    public LoanNotFoundException(int itemNo) {
        super("Can not find loan for itemNo: " + itemNo);
    }
    
}
