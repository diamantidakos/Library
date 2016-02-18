package com.mgiandia.library.domain;

/**
 * Το ISBN.
 * @author Νίκος Διαμαντίδης
 *
 */
public class ISBN {
    private String value;

    /**
     * Κατασκευαστής που λαμβάνει ως τιμή μία συμβολοσειρά για το ISBN.
     * @param value Η τιμή του ISBN
     */
    public ISBN(String value) {
        this.value = value;
    }


    /**
     * Επιστρέφει το ISBN ως συμβολοσειρά.
     * @return Το ISBN
     */
    public String getValue() {
        return value;
    }


    /**
     * Επαληθεύει την ορθότητα του ISBN.
     * @return {@code true} εάν είναι ορθό το ISBN
     */
    public final boolean isValid() {
        if (!hasValidLength()) {
            return false;
        }

        if (!hasValidCharacters()) {
            return false;
        }

        if (!hasValidCheckDigit()) {
            return false;
        }

        return true;
    }

    boolean hasValidLength() {
        return true;
    }

    boolean hasValidCharacters() {
        return true;
    }
    
    boolean hasValidCheckDigit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof ISBN)) {
            return false;
        }

        ISBN otherisbn = (ISBN) other;
        return value == null ? otherisbn.getValue() == null
                : value.equals(otherisbn.value);
    }


    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }


    @Override
    public String toString() {
        return value;
    }


}
