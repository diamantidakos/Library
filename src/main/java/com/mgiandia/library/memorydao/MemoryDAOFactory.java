package com.mgiandia.library.memorydao;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.DAOFactory;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;

/**
 * Το συγκεκριμένο εργοστάσιο παραγωγής αντικειμένων DAO.
 * Η υλοποίηση γίνεται με χρήση της μνήμης του υπολογιστή
 * για την αποθήκευση των αντικειμένων DAO.
 * @author Νίκος Διαμαντίδης
 *
 */
public class MemoryDAOFactory  extends DAOFactory{

    private BorrowerDAOMemory borrowerDaoMemory = new BorrowerDAOMemory();
    private ItemDAOMemory itemDaoMemory = new ItemDAOMemory();
    private LoanDAOMemory loanDaoMemory = new LoanDAOMemory();
    
    
    public BorrowerDAO getBorrowerDAO() {
        return borrowerDaoMemory;
    }

    public ItemDAO getItemDAO() {
        return itemDaoMemory;
    }

    public LoanDAO getLoanDAO() {
        return loanDaoMemory;
    }

}
