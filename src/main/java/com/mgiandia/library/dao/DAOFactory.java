package com.mgiandia.library.dao;

import com.mgiandia.library.LibraryException;

/**
 * Αφηρημένο εργοστάσιο των αντικείμενων πρόσβασης
 * δεδομένων (αντικείμενα DAO). Η κλάση που υλοποιεί
 * το αφηρημένο εργοστάσιο ορίζεται από την ιδιότητα
 * του συστήματος {@code daofactory}.
 * @author Νίκος Διαμαντίδης
 *
 */
public abstract class DAOFactory {

    private static DAOFactory factory = null;

    /**
     * Επιστρέφει το εργοστάσιο για την παραγωγή των
     * αντικειμένων DAO.
     * @return To εργοστάσιο για την παραγωγή των DAO αντικειμένων
     */
    public static DAOFactory getFactory() {
        if (factory == null) {
            String className = null;

            if (System.getProperty("daofactory") != null) {
                className = System.getProperty("daofactory");
            }

            try {
                factory = (DAOFactory) Class.forName(className).newInstance();
            } catch (Exception e) {
               throw new LibraryException(e);
            }
        }
        return factory;
    }


    /**
     * Επιστρέφει το αντικείμενο για τη διεπαφή {@link BorrowerDAO}.
     * @return Το αντικείμενο DAO.
     */
    public abstract BorrowerDAO getBorrowerDAO();


    /**
     * Επιστρέφει το αντικείμενο για τη διεπαφή {@link ItemDAO}.
     * @return Το αντικείμενο DAO.
     */
    public abstract ItemDAO getItemDAO();

    /**
     * Επιστρέφει το αντικείμενο για τη διεπαφή {@link LoanDAO}.
     * @return Το αντικείμενο DAO.
     */
    public abstract LoanDAO getLoanDAO();
}
