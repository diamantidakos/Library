package com.mgiandia.library.fines;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Η χαλαρή στρατηγική υπολογισμού του προστίμου.
 * Ο αριθμός ημερών για τον υπολογισμό του προστίμου βασίζεται στην
 * πρώτη ημέρα της εβδομάδας της ημερομηνίας επιστροφής.
 */
class RelaxedFineStrategy extends LinearFineStrategy {

    @Override
    protected long calculateStrategyOverdue(LocalDate due,
            LocalDate returnDate) {
        if (due == null || returnDate == null) {
            return 0;
        }
        
        LocalDate calculationDate = returnDate.with(DayOfWeek.MONDAY);
        long days = ChronoUnit.DAYS.between(due,calculationDate);
        return days;
    }

}
