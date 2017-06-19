package com.mgiandia.library.domain;

import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;


/**
 * Η κατηγορία δανειζομένου.
 * @author Νίκος Διαμαντίδης
 *
 */

public class BorrowerCategory {

    private int uid;
    private String description;
    private int maxLendingItems;
    private int maxLendingDays;
    private Money dailyFine;

    /**
     * Ο προκαθορισμένος κατασκευαστής.
     */
    public BorrowerCategory() { }

    /**
     * Βοηθητικός κατασκευαστής που αρχικοποιεί
     * τα βασικά στοιχεία της κατηγορίας δανειζομένου.
     * @param uid Ο μοναδικός κωδικός
     * @param description Περιγραφή κατηγορίας
     * @param maxLendingDays Μέγιστος αριθμός ημερών δανεισμού
     * @param maxLendingItems Μέγιστος αριθμός αντιτύπων
     * προς (ταυτόχρονο) δανεισμό
     * @param dailyFine Ημερήσιο πρόστιμο καθυστέρησης
     */
    public BorrowerCategory(int uid, String description, int maxLendingDays, int maxLendingItems, Money dailyFine)
    {
        this.uid = uid;
        this.description = description;
        this.maxLendingDays = maxLendingDays;
        this.maxLendingItems = maxLendingItems;
        this.dailyFine = dailyFine;
    }

    /**
     * Επιστρέφει τον μοναδικό κωδικό της κατηγορίας δανειζομένου.
     * @return Ο μοναδικός κωδικός της κατηγορίας δανειζομένου
     */
    public int getId() {
        return uid;
    }

    /**
     * Θέτει την περιγραφή της κατηγορίας δανειζομένου.
     * @param description Η περιγραφή της κατηγορίας δανειζομένου.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Επιστρέφει την περιγραφή της κατηγορίας δανειζομένου.
     * @return Η κατηγορία δανειζομένου.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Θέτει τον μέγιστο αριθμό ημερών δανεισμού ενός
     * αντιτύπου για την κατηγορία δανειζομένου.
     * @param maxLendingDays Ο μέγιστος αριθμός ημερών δανεισμού.
     */
    public void setMaxLendingDays(int maxLendingDays) {
        this.maxLendingDays = maxLendingDays;
    }

    /**
     * Επιστρέφει τον μέγιστο αριθμό ημερών δανεισμού
     * ενός αντιτύπου για την κατηγορία δανειζομένου.
     * @return Ο μέγιστος αριθμός ημερών δανεισμού.
     */
    public int getMaxLendingDays() {
        return maxLendingDays;
    }

    /**
     * Θέτει τον μέγιστο αριθμό αντιτύπων που μπορεί
     * ταυτόχρονα να δανειστεί κάποιος δανειζόμενος για
     * την κατηγορία δανειζομένου.
     * @param maxLendingItems Ο μέγιστος αριθμός αντιτύπων.
     */
    public void setMaxLendingItems(int maxLendingItems) {
        this.maxLendingItems = maxLendingItems;
    }


    /**
     * Επιστρέφει τον μέγιστο αριθμό αντιτύπων που
     * μπορεί ταυτόχρονα να δανειστεί κάποιος δανειζόμενος για
     * την κατηγορία δανειζομένου.
     * @return Ο μέγιστος αριθμός αντιτύπων
     */
    public int getMaxLendingItems() {
        return maxLendingItems;
    }


    /**
     * Θέτει το ημερήσιο πρόστιμο για την περίπτωση της
     * καθυστέρησης επιστροφής κάποιου αντιτύπου
     * για την κατηγορία δανειζομένου.
     * @param dailyFine Το ημερήσιο πρόστιμο.
     */
    public void setDailyFine(Money dailyFine) {
        this.dailyFine = dailyFine;
    }


    /**
     * Επιστρέφει το ημερήσιο πρόστιμο για την
     * περίπτωση της καθυστέρησης επιστροφής κάποιου αντιτύπου
     * για την κατηγορία δανειζομένου.
     * @return Το ημερήσιο πρόστιμο
     */
    public Money getDailyFine() {
        return dailyFine;
    }

    /**
     * Επιστρέφει την προθεσμία επιστροφής κάποιου
     * αντιτύπου για την κατηγορία δανειζομένου βάσει κάποιας
     * ημερομηνίας δανεισμού.
     * Επιστρέφει {@code null} αν η ημερομηνία δανεισμού είναι {@code null}.
     * @param loanDate Η ημερομηνία δανεισμού
     * @return Η προθεσμία επιστροφής
     */
    public SimpleCalendar getLoanDue(SimpleCalendar loanDate) {
        if (loanDate == null) {
            return null;
        }

        return loanDate.addDays(maxLendingDays);
    }

    /**
     * Επιστρέφει {@code true} εάν ο δανειζόμενος
     * της συγκεκριμένης κατηγορίας δανειζομένου
     * Μπορεί να δανειστεί κάποιο αντίτυπο.
     * @param pendingItems Ο αριθμός των αντιτύπων
     * που έχουν δανειστεί και δεν έχουν επιστραφεί.
     * @return {@code true} εάν μπορεί να δανειστεί κάποιο αντίτυπο.
     */
    public boolean canBorrow(int pendingItems) {
        return maxLendingItems > pendingItems;
    }

}
