package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.Borrower;

/**
 * Η διεπαφή DAO για την κλάση {@link Borrower}.
 * @author Νίκος Διαμαντίδης
 *
 */
public interface BorrowerDAO {

    /**
     * Η αναζήτηση ενός δανειζομένου με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου.
     * @return Το δανειζόμενο ή {@code null} εάν αυτός δεν βρεθεί.
     */
    Borrower find(int borrowerNo);
    
    
    /**
     * Αποθηκεύει ένα αντικείμενο στην εξωτερική πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param entity Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην εξωτερική πηγή δεδομένων.
     */
    void save(Borrower entity);
    
    /**
     * Διαγράφει το αντικείμενο από την εξωτερική πηγή δεδομένων.
     * @param entity Το αντικείμενο προς διαγραφή.
     */
    void delete(Borrower entity);
    
    /**
     * Επιστρέφει όλα τα αντικείμενα  από την εξωτερική πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    List<Borrower> findAll();

}
