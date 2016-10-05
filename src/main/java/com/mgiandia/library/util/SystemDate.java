package com.mgiandia.library.util;

import java.util.Calendar;

/**
 * Βοηθητική κλάση για τη λήψη της ημερομηνίας του συστήματος.
 * Η κλάση επιτρέπει να υποκατασταθεί η ημερομηνία του συστήματος με μία
 * προκαθορισμένη ημερομηνία. Η δυνατότητα αυτή
 * είναι ιδιαίτερη χρήσιμη για την εκτέλεση αυτόματων ελέγχων.
 * @author Νίκος Διαμαντίδης
 *
 */
public class SystemDate {

    /**
     * Απαγορεύουμε τη δημιουργία αντικείμενων.
     */
    protected SystemDate() { }

    private static SimpleCalendar stub;


    /**
     * Θέτει μία συγκεκριμένη ημερομηνία ως την ημερομηνία του συστήματος.
     * Η ημερομηνία αυτή επιστρέφεται από την {@link SystemDate#now()}.
     * Εάν αντί για προκαθορισμένης ημερομηνίας τεθεί
     * {@code null} τότε επιστρέφεται
     * η πραγματική ημερομηνία του συστήματος
     * @param stubDate Η ημερομηνία η οποία θα επιστρέφεται
     * ως ημερομηνία του συστήματος ή {@code null} για
     * να επιστρέφει την πραγματική ημερομηνία
     */
    protected static void setStub(SimpleCalendar stubDate) {
        stub = stubDate;
    }

    /**
     * Απομακρύνει το στέλεχος.
     */
    protected static void removeStub() {
        stub = null;
    }


    /**
     * Επιστρέφει την ημερομηνία του συστήματος ή μία
     * προκαθορισμένη ημερομηνία που έχει
     * τεθεί από την {@link SystemDate#setStub}.
     * @return Η ημερομηνία του συστήματος ή μία προκαθορισμένη ημερομηνία
     */
    public static SimpleCalendar now() {
        return stub == null ? new SimpleCalendar(Calendar.getInstance()) : stub;
    }
}
