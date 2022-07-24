package com.mgiandia.library.soap;

import javax.xml.ws.WebFault;

@WebFault(name = "canNotBorrow")
public class CanNotBorrowException extends Exception {
    
    private static final long serialVersionUID = 2216829289071819483L;
 
    public CanNotBorrowException(int borrowerId) {
        super("Can not borrow to id: " + borrowerId);
    }
}
