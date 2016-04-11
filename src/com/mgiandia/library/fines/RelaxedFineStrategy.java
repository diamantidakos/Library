package com.mgiandia.library.fines;


import java.util.Calendar;

import com.mgiandia.library.util.SimpleCalendar;


/**
 * Η χαλαρή στρατηγική υπολογισμού του προστίμου.
 * Ο αριθμός ημερών για τον υπολογισμό του προστίμου βασίζεται στην
 * πρώτη ημέρα της εβδομάδας της ημερομηνίας επιστροφής.
 */
class RelaxedFineStrategy extends LinearFineStrategy {

    @Override
    protected long calculateStrategyOverdue(SimpleCalendar due,
            SimpleCalendar returnDate) {
        if (due == null || returnDate == null) {
            return 0;
        }
        Calendar calculationDate = Calendar.getInstance();
        calculationDate.setTimeInMillis(
                returnDate.getJavaCalendar().getTimeInMillis());
        calculationDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        long overdue = due.durationInDays(new SimpleCalendar(calculationDate));
        return overdue > 0 ? overdue : 0;
    }

}
