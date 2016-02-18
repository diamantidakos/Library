package com.mgiandia.library.ui.loan;

import com.mgiandia.library.ui.View;

/**
 * Η όψη της επιστροφής αντιτύπου
 * @author Νίκος Διαμαντίδης
 *
 */
public interface ReturnView extends View {
    
    /**
     * Θέτει τον presenter της όψης
     * @param presenter
     */
    void setPresenter(ReturnPresenter presenter);
       
    /**
     * Επιστρέφει τον τρέχοντα αριθμό αντιτύπου
     */
    int getItemNumber();
}
