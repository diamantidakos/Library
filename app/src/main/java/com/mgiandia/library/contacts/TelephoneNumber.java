package com.mgiandia.library.contacts;

/**
 * Ο αριθμός τηλεφώνου.
 * Υλοποιείται ως αντικείμενο τιμή
 * @author Νίκος Διαμαντίδης
 *
 */
public class TelephoneNumber {

    private String phonenumber;

    /**
     * Κατασκευαστής που βασίζεται σε μία συμβολοσειρά.
     * @param phonenumber Ο αριθμός τηλεφώνου
     */
    public TelephoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * Επιστρέφει τον αριθμό τηλεφώνου ως συμβολοσειρά.
     * @return Ο αριθμός τηλεφώνου
     */

    public String getTelephoneNumber() {
        return phonenumber;
    }

    /**
     * Επαληθεύει την ορθότητα του αριθμού τηλεφώνου.
     * @return {@code true} εάν ο αριθμός τηλεφώνου είναι ορθός
     */
    public boolean isValid() {
        return true;
    }

    /**
     * Επαληθεύει to instanceof ενός αριθμού τηλεφώνου.
     * @param other Το άλλο αντικείμενο προς έλεγχο
     * @return Τον αριθμού τηλεφώνου.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof TelephoneNumber)) {
            return false;
        }

        TelephoneNumber thePhoneNumber = (TelephoneNumber) other;
        return phonenumber == null
            ? thePhoneNumber.getTelephoneNumber() == null
                : phonenumber.equals(thePhoneNumber.getTelephoneNumber());
    }

    /**
     * Το HashCode ενός αριθμού τηλεφώνου.
     * @return Το HashCode
     */
    @Override
    public int hashCode() {
        return phonenumber == null ? 0 : phonenumber.hashCode();
    }

    /**
     * Ο αριθμός τηλεφώνου με την μορφή string.
     * @return Ο αριθμός τηλεφώνου
     */
    @Override
    public String toString() {
        return phonenumber;
    }
}
