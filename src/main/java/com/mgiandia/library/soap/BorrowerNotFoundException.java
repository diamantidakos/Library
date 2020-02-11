package com.mgiandia.library.soap;

import javax.xml.ws.WebFault;

@WebFault(name = "borrowerNotFound")
public class BorrowerNotFoundException extends Exception {
    
    

    private static final long serialVersionUID = -4285169069469813582L;

    public BorrowerNotFoundException(int borrowerId) {
        super("Borrower not found with id: " + borrowerId);
    }
}