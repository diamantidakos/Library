package com.mgiandia.library;


@SuppressWarnings("serial")
public class LibraryException extends RuntimeException {

    public LibraryException() { }
    
    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryException(Throwable cause) {
        super(cause);
    }
}
