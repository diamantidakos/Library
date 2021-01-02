package com.mgiandia.library.domain;

import com.mgiandia.library.LibraryException;

public abstract class ItemObjectState  {

    void available(Item item) {
        throw new LibraryException();
    }

    Loan borrow(Item item, Borrower borrower) {  
    	throw new LibraryException();    
    }
   
    void lost(Item item) {
    	throw new LibraryException();
        
    }

    void withdraw(Item item) {
    	throw new LibraryException();        
    }

    abstract ItemState getState();
}
