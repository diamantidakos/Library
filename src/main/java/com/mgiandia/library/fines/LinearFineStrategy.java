package com.mgiandia.library.fines;

import com.mgiandia.library.util.Money;

import java.time.LocalDate;

import com.mgiandia.library.domain.Loan;


/**
 * Αφηρημένη κλάση που υλοποιεί τη στρατηγική υπολογισμού των προστίμων.
 * Υποθέτει ότι ο υπολογισμός του προστίμου γίνει γραμμικά
 * δηλαδή Πρόστιμο = ημερήσιο πρόστιμο * ημέρες καθυστέρησης.
 * Οι ημέρες καθυστέρησης παρέχονται από τη μέθοδο
 * υπόδειγμα {@code calculateOverdue}
 * Οι κλάσεις που την κληρονομούν θα πρέπει
 * να παρέχουν την υλοποίηση υπολογισμού
 * των ημερών καθυστέρησης ανάλογα με τη διαφορετική πολιτική του υπολογισμού
 */
abstract class LinearFineStrategy implements FineStrategy {

    /**
     * Επιστρέφει το πρόστιμο καθυστέρησης για κάποιο δανεισμό.
     * Επιστρέφει 0 εάν {@code loan} είναι {@code null}.
     * Επιστρέφει 0 εάν δεν έχει οριστεί δανειζόμενος
     * Επιστρέφει 0 εάν δεν έχει οριστεί κατηγορία δανειζομένου
     * @param loan Ο δανεισμός
     * @return Το πόστιμο καθυστέρησης
     */
    public final Money calculateFine(Loan loan) {
        if (loan == null) {
            return Money.euros(0);
        }

        if (loan.getBorrower() == null) {
            return Money.euros(0);
        }

        if (loan.getBorrower().getCategory() == null) {
            return Money.euros(0);
        }

        return calculateFine(loan.getDue(),
                loan.getReturnDate(),
                loan.getBorrower().getDailyFine());
    }


    /**
     * Επιστρέφει το πρόστιμο καθυστέρησης για τα δεδομένα
     * κάποιου δανεισμού δηλαδή την προθεσμία επιστροφής,
     * την ημερομηνία επιστροφής και το ημερήσιο πρόστιμο
     * Επιστρέφει 0 εάν {@code due} είναι {@code null}.
     * Επιστρέφει 0 εάν {@code returnDate} είναι {@code null}.
     * @param due Προθεσμία επιστροφής
     * @param returnDate Ημερομηνία επιστροφής
     * @param dailyFine Ημερήσιο πρόστιμο
     * @return Το πόστιμο καθυστέρησης
     */
    public final Money calculateFine(LocalDate due,
    		LocalDate returnDate, Money dailyFine) {
        if (due == null || returnDate == null) {
            return Money.euros(0);
        }
        
        long overdue = calculateStrategyOverdue(due, returnDate);
        if (overdue <= 0) {
            return Money.euros(0);
        }

        return dailyFine.times(overdue);
    }


    /**
     * Ο αριθμός των ημερών στον οποίο αναφέρεται ο υπολογισμός προστίμου.
     * ανάλογα με τη στρατηγική υπολογισμού
     * @param due Η προθεσμία επιστροφής
     * @param returnDate Η ημερομηνία επιστροφής
     * @return Ο αριθμός των ημερών βάσει του οποίου υπολογίζεται το πρόστιμο
     */
    protected abstract long calculateStrategyOverdue(LocalDate due,
    		LocalDate returnDate);

}
