package com.mgiandia.library.memorydao;


import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.domain.Loan;

/**
 * Η υλοποίηση των αντικείμενων πρόσβασης δεδομένων (DAO)
 * για την κλάση {@link Loan} όπου η εξωτερική πηγή δεδομένων
 * είναι η μνήμη.
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanDAOMemory implements LoanDAO{

    protected static List<Loan> entities = new ArrayList<Loan>();

    /**
     * Επιστρέφει το δανεισμό για κάποιο αριθμό εισαγωγής.
     * @param uid Ο αριθμός εισαγωγής του δανεισμού
     * @return Ο δανεισμός
     */
    public Loan find(int uid)
    {
        for(Loan loan : entities)
            if(loan.getId() == uid)
                return loan;

        return null;
    }

    /**
     * Διαγράφει το αντικείμενο από την εξωτερική πηγή δεδομένων.
     * @param entity Το αντικείμενο προς διαγραφή.
     */
    public void delete(Loan entity) {
        entities.remove(entity);    
    }

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την εξωτερική πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    public List<Loan> findAll() {
        return new ArrayList<Loan>(entities);
    }

    /**
     * Αποθηκεύει ένα αντικείμενο στην εξωτερική πηγή
     * δεδομένων. Το αντικείμενο μπορεί να είναι κάποιο
     * νέο αντικείμενο που δεν υπάρχει στην πηγή δεδομένων
     * ή κάποιο το οποίο ήδη υπάρχει και ενημερώνεται η
     * κατάστασή του.
     * @param entity Το αντικείμενο του οποίου η κατάσταση
     * αποθηκεύεται στην εξωτερική πηγή δεδομένων.
     */
    public void save(Loan entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    /**
     * Επιστρέφει το δανεισμό σε εκκρεμότητα για κάποιο αριθμό
     * εισαγωγής αντιτύπου. Αν δεν υπάρχει δανεισμός σε
     * εκκρεμότητα επιστρέφει {@code null}.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου
     * @return Ο δανεισμός που είναι σε εκκρεμότητα.
     */
    public Loan findPending(int itemNo) {
        for(Loan loan : entities) {
            if (loan.getItem().getItemNumber() == itemNo &&
                    loan.isPending()) {
                return loan;
            }
        }
        return null;
    }

    /**
     * Επιστρέφει όλους τους εκκρεμείς δανεισμούς.
     * @return Ο κατάλογος των εκκρεμών δανεισμών
     */
    public List<Loan> findAllPending() {
        List<Loan> allLoans = findAll();
        List<Loan> pending = new ArrayList<Loan>();
        
        for(Loan loan : allLoans) {
            if (loan.isPending()) {
                pending.add(loan);
            }
        }
        
        return pending;
    }

    /**
     * Επιστρέφει τον επόμενο διαθέσιμο κωδικό που μπορεί να χρησιμοποιηθεί από ένα δανεισμό.
     * @return Ο επόμενος διαθέσιμος κωδικός δανεισμού.
     */
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}