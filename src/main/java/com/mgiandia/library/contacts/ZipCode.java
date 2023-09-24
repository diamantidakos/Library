package com.mgiandia.library.contacts;

import java.io.Serializable;

/**
 * Ο Ταχυδρομικός κώδικας.
 * @author Νίκος Διαμαντίδης
 *
 */
public class ZipCode implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String zipcode;

    /**
     * Κατασκευαστής που βασίζεται σε ταχυδρομικό κώδικα ως συμβολοσειρά.
     * @param zipcode Ο ταχυδρομικό κώδικας
     */
    public ZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Επιστρέφει τον ταχυδρομικό κώδικα ως συμβολοσειρά.
     * @return Ο ταχυδρομικός κώδικας
     */
    public String getCode() {
        return zipcode;
    }

    /**
     * Επαληθεύει την ορθότητα του ταχυδρομικού κώδικα.
     * @return {@code true} εάν είναι ορθός ο ταχυδρομικός κώδικας
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

        if (!(other instanceof ZipCode)) {
            return false;
        }

        ZipCode theZipCode = (ZipCode) other;
        return zipcode == null
            ? theZipCode.zipcode == null
            : zipcode.equals(theZipCode.zipcode);
    }


    @Override
    public int hashCode() {
        return zipcode == null ? 0 : zipcode.hashCode();
    }

    @Override
    public String toString() {
        return zipcode;
    }
}
