package com.mgiandia.library.ui.loan;

import com.mgiandia.library.ui.View;

/**
 * Η όψη του δανεισμού αντιτύπου
 * @author Νίκος Διαμαντίδης
 *
 */
public interface LoanView extends View {
    
    /**
     * Θέτει τον presenter που διαχειρίζεται την όψη.
     * @param presenter Ο presenter
     */
    void setPresenter(LoanPresenter presenter);

    /**
     * Ελέγχει για το αν είναι διαθέσιμη η ενέργεια του δανεισμού
     * @return true αν είναι διαθέσιμη η ενέργεια του δανεισμού
     */
    boolean isLoanActionEnabled();
       
    /**
     * Θέτει για το αν είναι διαθέσιμη η ενέργεια του δανεισμού
     * @param enabled
     */
    void setLoanActionEnabled(boolean enabled);
    
    /**
     * Επιστρέφει τον τρέχοντα αριθμό δανειζομένου
     * @return Ο τρέχων αριθμός δανειζομένου
     */
    int getBorrowerNo();
    
    /**
     * Επιστρέφει τον τρέχοντα αριθμό αντιτύπου
     * @return Ο τρέχων αριθμός αντιτύπου
     */
    int getItemNumber();
    
    /**
     * Θέτει το επώνυμο του δανειζομένου
     * @param name Το επώνυμο του δανειζομένου
     */
    void setBorrowerLastName(String name);
    
    /**
     * Θέτει το όνομα του δανειζομένου 
     * @param name Το όνομα του δανειζομένου
     */
    void setBorrowerFirstName(String name);
    
    
    /**
     * Θέτει τον τίτλο του βιβλίου του τρέχοντος αντιτύπου
     * @param name Ο τίτλος του βιβλίου
     */
    void setBookTitle(String name);
}
