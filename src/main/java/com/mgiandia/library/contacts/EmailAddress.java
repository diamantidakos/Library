package com.mgiandia.library.contacts;


/**
 * Η διεύθυνση ηλεκτρονικού ταχυδρομείου.
 * Υλοποιείται ως αντικείμενο τιμή (value object).
 * @author  Νίκος Διαμαντίδης
 *
 */
public class EmailAddress {
    private String value;

    /**
     * Δημιουργία βάσει της συμβολοσειράς της διεύθυνσης.
     * @param email Το e-mail.
     */
    public EmailAddress(String email) {
        this.value = email;
    }

    /**
     * Επιστρέφει τη διεύθυνση ως συμβολοσειρά.
     * @return Η διεύθυνση ηλεκτρονικού ταχυδρομείου.
     */
    public String getAddress() {
        return value;
    }

    /**
     * Επαληθεύει την ορθότητα της διεύθυνσης ηλεκτρονικού ταχυδρομείου.
     * @return {@code true} εάν είναι ορθή η διεύθυνση
     * του ηλεκτρονικού ταχυδρομείου
     */
    public boolean isValid() {
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

        if (!(other instanceof EmailAddress)) {
            return false;
        }

        EmailAddress theEmail = (EmailAddress) other;
        return value == null ? theEmail.getAddress() == null
                : value.equals(theEmail.getAddress());
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
