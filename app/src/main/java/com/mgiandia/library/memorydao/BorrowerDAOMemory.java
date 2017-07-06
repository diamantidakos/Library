package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BorrowerDAOMemory implements BorrowerDAO {

    protected static List<Borrower> entities = new ArrayList<Borrower>();

    /**
     * Διαγράφει το αντικείμενο από την εξωτερική πηγή δεδομένων.
     * @param entity Το αντικείμενο προς διαγραφή.
     */
    public void delete(Borrower entity) {
        entities.remove(entity);    
    }

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την εξωτερική πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    public List<Borrower> findAll() {
        return new ArrayList<Borrower>(entities);
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
    public void save(Borrower entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    /**
     * Η αναζήτηση ενός δανειζομένου με βάση τον αριθμό δανειζομένου.
     * @param borrowerNo Ο αριθμός δανειζομένου.
     * @return Το δανειζόμενο ή {@code null} εάν αυτός δεν βρεθεί.
     */
    public Borrower find(int borrowerNo) {
        for(Borrower borrower : entities) {
            if (borrower.getBorrowerNo() == borrowerNo ) {
                return borrower;
            }
        }

        return null;
    }

    /**
     * Επιστρέφει τον επόμενο διαθέσιμο κωδικό που μπορεί να χρησιμοποιηθεί από έναν δανειζόμενο.
     * @return Ο επόμενος διαθέσιμος κωδικός δανειζομένου.
     */
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getBorrowerNo()+1 : 1);
    }
}
