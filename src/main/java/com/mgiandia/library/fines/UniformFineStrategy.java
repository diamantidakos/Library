package com.mgiandia.library.fines;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



/**
 * Η ομοιόμορφη στρατηγική υπολογισμού του προστίμου.
 * Ο αριθμός των ημερών που υπολογίζεται το πρόστιμο βασίζεται στην
 * ημερομηνία επιστροφής
 */
class UniformFineStrategy extends LinearFineStrategy {
    @Override
    protected long calculateStrategyOverdue(LocalDate due,
            LocalDate returnDate) {
        if (due == null || returnDate == null) {
            return 0;
        }

        return  ChronoUnit.DAYS.between(due,returnDate);
      
    }
}
