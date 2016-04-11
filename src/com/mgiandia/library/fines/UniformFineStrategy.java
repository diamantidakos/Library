package com.mgiandia.library.fines;


import com.mgiandia.library.util.SimpleCalendar;


/**
 * Η ομοιόμορφη στρατηγική υπολογισμού του προστίμου.
 * Ο αριθμός των ημερών που υπολογίζεται το πρόστιμο βασίζεται στην
 * ημερομηνία επιστροφής
 */
class UniformFineStrategy extends LinearFineStrategy {
    @Override
    protected long calculateStrategyOverdue(SimpleCalendar due,
            SimpleCalendar returnDate) {
        if (due == null || returnDate == null) {
            return 0;
        }

        return due.durationInDays(returnDate);
    }
}
