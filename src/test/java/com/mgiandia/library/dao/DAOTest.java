package com.mgiandia.library.dao;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.ISBN;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;


/**
 * Κλάση ελέγχου για τις βασικές πράξεις των αντικειμένων πρόσβασης δεδομένων
 * @author Νίκος Διαμαντίδης
 *
 */

public class DAOTest {


    
    private BorrowerDAO borrowerDao;
    private ItemDAO itemDao;
    private LoanDAO loanDao;
    
    
    private static final int INITIAL_BORROWER_COUNT = 2;
    private static final int INITIAL_ITEM_COUNT = 5;    
    private static final int INITIAL_LOAN_COUNT = 0;
    
    private static final int BORROWER_NO_FOR_NEW_LOAN = 1;
    private static final int ITEM_NO_FOR_NEW_LOAN = 1;
    
    

    
    @BeforeEach
    public void setUp() {                
    	Initializer dataHelper = new MemoryInitializer();
    	dataHelper.prepareData();
        borrowerDao = new BorrowerDAOMemory();
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
    }
    
    
    
    /**
     * Αναζήτηση δανειζομένου που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingBorrower() {
        Borrower borrower = borrowerDao.find(2);
        Assertions.assertEquals("Νίκος", borrower.getFirstName());                
    }
    
    /**
     * Αναζήτηση δανειζομένου που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingBorrower() {        
        Borrower borrower = borrowerDao.find(4711);
        Assertions.assertNull(borrower);    
    }
        
    /**
     * Εύρεση καταλόγου δανειζομένων
     */
    @Test
    public void listAllBorrowers() {        
        List<Borrower> allBorrowers = borrowerDao.findAll();
        Assertions.assertEquals(INITIAL_BORROWER_COUNT, allBorrowers.size());        
    }
    
    /**
     * Αποθήκευση δανειζομένου
     */
    @Test
    public void saveBorrower() {        
        Borrower borrower = new Borrower(5000, "Giannis", "Martinopoulos", null, null, null);
        borrowerDao.save(borrower);
        Assertions.assertEquals(INITIAL_BORROWER_COUNT + 1, borrowerDao.findAll().size());
        Assertions.assertNotNull(borrowerDao.find(borrower.getBorrowerNo()));
        Assertions.assertTrue(borrowerDao.findAll().contains(borrower));
    }
    
    
    /**
     * Διαγραφή δανειζομένου
     */
    @Test
    public void deleteBorrower() {
        List<Borrower> allBorrowers = borrowerDao.findAll();
        Borrower borrower = allBorrowers.get(0);
        borrowerDao.delete(borrower );
        allBorrowers = borrowerDao.findAll();
        Assertions.assertEquals(INITIAL_BORROWER_COUNT - 1, allBorrowers.size());
        Assertions.assertNull(borrowerDao.find(borrower.getBorrowerNo()));
    }
    
    /**
     * Αναζήτηση αντιτύπου που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingItem() {
        String EXPECTED_ISBN_FROM_ITEM = "1";
        
        Item item = itemDao.find(1);
        Assertions.assertEquals(EXPECTED_ISBN_FROM_ITEM , item.getBook().getIsbn().getValue());
    }
    
    
    /**
     * Αναζήτηση αντιτύπου που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingItem() {
        Item item = itemDao.find(4711);
        Assertions.assertNull(item);
    }
    
    /**
     * Κατάλογος όλων των αντιτύπων
     */
    @Test
    public void listAllItems() {
        List<Item> allItems = itemDao.findAll();        
        Assertions.assertEquals(INITIAL_ITEM_COUNT, allItems.size());        
    }
    
    /**
     * Αποθήκευση αντιτύπου
     */
    @Test
    public void saveItem() {
        Book book = new Book("One Title", new ISBN("9999"), null, 0, null);
        Item item = new Item(10);
        item.setBook(book);
        itemDao.save(item);
        List<Item> allItems = itemDao.findAll();
        Assertions.assertEquals(INITIAL_ITEM_COUNT + 1, allItems.size());
        Assertions.assertNotNull(itemDao.find(item.getItemNumber()));
        Assertions.assertTrue(allItems.contains(item));
    }
    
    /**
     * Διαγραφή αντιτύπου
     */
    @Test
    public void deleteItem() {        
        Item item = itemDao.findAll().get(0);
        item.setBook(null);
        itemDao.delete(item);
        List<Item> allItems = itemDao.findAll();
        Assertions.assertEquals(INITIAL_ITEM_COUNT - 1, allItems.size());
        Assertions.assertNull(itemDao.find(item.getItemNumber()));
        Assertions.assertFalse(allItems.contains(item));        
    }
    
    
    /**
     * Αποθήκευση δανεισμού    
     */
    @Test
    public void saveLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        List<Loan> allLoans = loanDao.findAll();
        Assertions.assertEquals(INITIAL_LOAN_COUNT + 1, allLoans.size());
        Assertions.assertNotNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));        
        
    }
    
    /**
     * Αναζήτηση δανεισμού σε εκκρεμότητα (χωρίς την επιστροφή)
     */
    @Test
    public void findExistingPendingLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        Assertions.assertNotNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));        
    }
    
    /**
     * Αναζήτηση δανεισμού σε εκκρεμότητα όταν αυτός δεν υπάρχει
     */
    @Test
    public void findNonExistingPendingLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        Assertions.assertNull(loanDao.findPending(4711));
    }
    
    /**
     * Αναζήτηση δανεισμού σε εκκρεμότητα μετά και την επιστροφή αντιτύπου
     */
    @Test
    public void findPendingLoanWhenItemIsReturned() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        loan.returnItem();
        loanDao.save(loan);
        Assertions.assertNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));
    }
    
    
    private Loan CreateNewLoan() {
        Borrower borrower = borrowerDao.find(BORROWER_NO_FOR_NEW_LOAN);
        Item item = itemDao.find(ITEM_NO_FOR_NEW_LOAN);
        Loan loan = item.borrow(borrower);
        return loan;
    }
}
