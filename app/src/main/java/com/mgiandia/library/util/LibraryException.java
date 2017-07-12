package com.mgiandia.library.util;

/**
 * @author Νίκος Διαμαντίδης
 */

@SuppressWarnings("serial")
public class LibraryException extends RuntimeException {

    /**
     * Κάνει throw ένα run time exception.
     */
    public LibraryException() { }

    /**
     * Κάνει throw ένα run time exception
     * με μήνυμα message.
     * @param message Το μύνημα
     */
    public LibraryException(String message) {
        super(message);
    }

    /**
     * Κάνει throw ένα run time exception
     * με μήνυμα message και αιτία cause.
     * @param message Το μύνημα
     * @param cause Η αιτία
     */
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Κάνει throw ένα run time exception
     * με αιτία cause.
     * @param cause Η αιτία
     */
    public LibraryException(Throwable cause) {
        super(cause);
    }
}
