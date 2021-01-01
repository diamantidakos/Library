package com.mgiandia.library.fines;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Η αυστηρή στρατηγική υπολογισμού του προστίμου.
 * Ο αριθμός των ημερών που υπολογίζεται το πρόστιμο βασίζεται στην
 * τελευταία ημέρα της εβδομάδας της ημερομηνίας επιστροφής
 */
class StrictFineStrategy extends LinearFineStrategy {

    @Override
    protected long calculateStrategyOverdue(LocalDate due,
            LocalDate returnDate) {
        if (due == null || returnDate == null) {
            return 0;
        }

        if (due.equals(returnDate)) {
            return 0;
        }

        if (due.isAfter(returnDate)) {
            return 0;
        }

        LocalDate calculationDate = returnDate.with(DayOfWeek.SUNDAY);
        
        return ChronoUnit.DAYS.between(due,calculationDate);
    }

}
