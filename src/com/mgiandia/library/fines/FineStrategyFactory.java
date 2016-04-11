package com.mgiandia.library.fines;

/**
 * Το εργοστάσιο που επιστρέφει αντικείμενα της
 * στρατηγικής επιβολής προστίμων που δηλώνει
 * η διεπαφή {@link FineStrategy}.
 * Η κλάση που υλοποιεί τη διεπαφή ορίζεται από την
 * ιδιότητα του συστήματος {@code finestrategy}
 * @author Νίκος Διαμαντίδης
 *
 */
public final class FineStrategyFactory {

    /**
     * Δεν επιτρέπουμε τη δημιουργία αντικειμένων.
     */
    private FineStrategyFactory() { }

    /**
     * Επιστρέφει το αντικείμενο της στρατηγικής επιβολής
     * προστίμου σύμφωνα με τις τρέχουσες ρυθμίσεις του
     * συστήματος.
     * @return Η στρατηγική επιβολής προστίμου
     */
    public static FineStrategy getStrategy() {
        FineStrategy strategy = null;
        String className =
            "com.mgiandia.library.fines.UniformFineStrategy";
        if (System.getProperty("finestrategy") != null) {
            className = System.getProperty("finestrategy");
        }

        try {
            strategy = (FineStrategy) Class.forName(className).newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return strategy;
    }
}
