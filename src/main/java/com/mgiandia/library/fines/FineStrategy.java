package com.mgiandia.library.fines;


import java.time.LocalDate;

import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;

/**
 * Η στρατηγική επιβολής προστίμων για την.
 * καθυστερημένη επιστροφή αντιτύπων
 * @author Νίκος Διαμαντίδης
 *
 */
public interface FineStrategy {

    /**
     * Ο υπολογισμός του προστίμου καθυστέρησης
     * για κάποιο δανεισμό.
     * @param loan Ο δανεισμός
     * @return Το ποσό του προστίμου
     */
     Money calculateFine(Loan loan);

    /**
     * Ο υπολογισμός του προστίμου καθυστέρησης
     * για τα δεδομένα κάποιου δανεισμού.
     * @param due Προθεσμία επιστροφής
     * @param returnDate Ημερομηνία επιστροφής
     * @param dailyFine Ημερήσιο πρόστιμο
     * @return Το ποσό του ποστίμου
     */ 
     Money calculateFine(LocalDate due,
             LocalDate returnDate, Money dailyFine);

}