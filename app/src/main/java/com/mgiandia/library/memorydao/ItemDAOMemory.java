package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.domain.Item;

/**
 * Η υλοποίηση των αντικειμένων πρόσβασης δεδομένων (DAO) για
 * την κλάση {@link Item}
 * @author Νίκος Διαμαντίδης
 *
 */
public class ItemDAOMemory implements ItemDAO { 
    protected static List<Item> entities = new ArrayList<Item>();

    /**
     * Διαγράφει το αντικείμενο από την εξωτερική πηγή δεδομένων.
     * @param entity Το αντικείμενο προς διαγραφή.
     */
    public void delete(Item entity) {
        entities.remove(entity);    
    }

    /**
     * Επιστρέφει όλα τα αντικείμενα  από την εξωτερική πηγή δεδομένων.
     * @return Ο κατάλογος των αντικειμένων
     */
    public List<Item> findAll() {
        return new ArrayList<Item>(entities);
    }

    public List<Item> findByBookTitle(String title)
    {
        List<Item> result = new ArrayList<>();

        for (Item item : entities)
        {
            if (item.getBook().getTitle().equals(title))
            {
                result.add(item);
            }
        }

        return result;
    }

    public List<Item> findByBookTitle(String itemTitle, String bookTitle)
    {
        List<Item> result = new ArrayList<>();

        for (Item item : entities)
        {
            if (item.getBook().getTitle().contains(itemTitle) && item.getBook().getTitle().equals(bookTitle))
            {
                result.add(item);
            }
        }

        return result;
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
    public void save(Item entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }

    /**
     * Επιστρέφει το αντίτυπο για κάποιο αριθμό εισαγωγής.
     * @param itemNo Ο αριθμός εισαγωγής του αντιτύπου
     * @return Το αντίτυπο
     */
    public Item find(int itemNo) {
        for(Item item : entities) {
            if (item.getItemNumber() == itemNo) {
                return item;
            }
        }
        return null;
    }

    /**
     * Επιστρέφει τον επόμενο διαθέσιμο κωδικό που μπορεί να χρησιμοποιηθεί από ένα αντίτυπο.
     * @return Ο επόμενος διαθέσιμος κωδικός αντιτύπου.
     */
    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getItemNumber()+1 : 1);
    }
}
