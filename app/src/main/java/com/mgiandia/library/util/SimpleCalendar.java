package com.mgiandia.library.util;

import java.util.Calendar;


/**
 * Αμετάβλητη κλάση για τη διαχείριση ημερομηνιών
 * αγνοώντας την ώρα.
 * @author Νίκος Διαμαντίδης
 *
 */
public class SimpleCalendar implements  Comparable<SimpleCalendar> {
    private static final long MILLIS_PER_DAY = 86400000;
    private Calendar date;

    /**
     * Κατασκευάζει μία ημερομηνία με βάση το έτος,
     * το μήνα και την ημέρα του μήνα.
     * @param year Το έτος
     * @param month Ο μήνας από 1 έως 12
     * @param day Η ημέρα του μήνα
     */
    public SimpleCalendar(int year, int month, int day) {
        date = Calendar.getInstance();
        date.set(year, month - 1, day);
        trimToDays(this.date);
    }

    /**
     * Κατασκευάζει μία ημερομηνία λαμβάνοντας.
     * ως παράμετρο αντικείμενο της κλάσης {@code Calendar}
     * @param date Η ημερομηνία
     */
    public SimpleCalendar(Calendar date) {
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(date.getTimeInMillis());
        trimToDays(this.date);
    }

    private void trimToDays(Calendar javaDate) {
        javaDate.set(Calendar.HOUR_OF_DAY, 0);
        javaDate.set(Calendar.MINUTE, 0);
        javaDate.set(Calendar.SECOND, 0);
        javaDate.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Η διάρκεια σε ημέρες σε σχέση με μία άλλη ημερομηνία.
     * @param other Η δεύτερη ημερομηνία για την οποία
     * υπολογίζεται η διάρκεια
     * @return Ο αριθμός των ημερών. Θετικός αριθμός ημερών
     * σημαίνει ότι η άλλη ημερομηνία είναι μεταγενέστερη,
     * ενώ αρνητικός το αντίθετο.
     */
    public long durationInDays(SimpleCalendar other) {
        long timeDiff = other.date.getTimeInMillis() - date.getTimeInMillis();
        return timeDiff / MILLIS_PER_DAY;
    }

    /**
     * Επιστρέφει το έτος της ημερομηνίας.
     * @return Το έτος
     */
    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    /**
     * Επιστρέφει το μήνα της ημερομηνίας (1-12).
     * @return Ο μήνας
     */
    public int getMonth() {
        return date.get(Calendar.MONTH) + 1;
    }

    /**
     * Επιστρέφει την ημέρα σε του μήνα.
     * @return Η ημέρα του μήνα
     */
    public int getDayOfMonth() {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Επιστρέφει την ημέρα της εβδομάδας της ημερομηνίας.
     * @return Η ημέρα της εβδομάδας
     */
    public int getDayOfWeek() {
        return date.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Επιστρέφει {@code true} αν η ημερομηνία είναι.
     * μεταγενέστερη μίας άλλης ημερομηνίας
     * @param other Η άλλη ημερομηνία
     * @return {@code true} αν η ημερομηνία είναι
     * μεταγενέστερη της άλλης
     */
    public boolean after(SimpleCalendar other) {
        if (equals(other)) {
            return false;
        }

        return date.after(other.date);
    }

    /**
     * Επιστρέφει {@code true} αν η ημερομηνία είναι.
     * προγενέστερη μίας άλλης ημερομηνίας
     * @param other Η άλλη ημερομηνία
     * @return {@code true} αν η ημερομηνία είναι
     * προγενέστερη της άλλης
     */
    public boolean before(SimpleCalendar other) {
        if (equals(other)) {
            return false;
        }

        return date.before(other.date);
    }

    /**
     * Επιστρέφει μία ημερομηνία προσθέτοντας κάποιο
     * αριθμό ημερών.
     * @param days Ο αριθμός των ημερών που προστίθενται
     * @return Η νέα ημερομηνία
     */
    public SimpleCalendar addDays(int days) {
        Calendar newDate = Calendar.getInstance();
        newDate.setTimeInMillis(date.getTimeInMillis());
        newDate.add(Calendar.DAY_OF_MONTH, days);
        return new SimpleCalendar(newDate);
    }

    /**
     * Επιστρέφει μία ημερομηνία τύπου {@code Calendar}.
     * @return Η ημερομηνία
     */
    public Calendar getJavaCalendar() {
        Calendar javaCalendar = Calendar.getInstance();
        javaCalendar.setTimeInMillis(date.getTimeInMillis());
        trimToDays(javaCalendar);
        return javaCalendar;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(SimpleCalendar other) {
        return date.compareTo(other.date);
    }

    /**
     * Η ισότητα βασίζεται σε όλα τα πεδία της διεύθυνσης.
     * @param other Το άλλο αντικείμενο προς έλεγχο
     * @return  {@code true} αν τα αντικείμενα είναι ίσα
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof SimpleCalendar)) {
            return false;
        }

        SimpleCalendar theDate = (SimpleCalendar) other;

        if (getYear() != theDate.getYear()) {
            return false;
        }

        if (getMonth() != theDate.getMonth()) {
            return false;
        }

        if (getDayOfMonth() != theDate.getDayOfMonth()) {
            return false;
        }

        return true;
    }

    /**
     * Το HashCode μίας ημερομηνίας
     * @return Το HashCode
     */
    @Override
    public int hashCode() {
        return date == null ? 0 : date.hashCode();
    }
}
