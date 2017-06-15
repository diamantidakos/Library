package com.mgiandia.library.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;
import com.mgiandia.library.contacts.ZipCode;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.ISBN;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerCategoryDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.CountryDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.PublisherDAOMemory;

/**
 * Κλάση ελέγχου για τις βασικές πράξεις των αντικειμένων πρόσβασης δεδομένων
 * @author Νίκος Διαμαντίδης
 *
 */

public class DAOTest {

    private BorrowerCategoryDAO borrowerCategoryDao;
    private BookDAO bookDao;
    private PublisherDAO publisherDao;
    private BorrowerDAO borrowerDao;
    private ItemDAO itemDao;
    private LoanDAO loanDao;
    private AuthorDAO authorDao;
    private CountryDAO countryDao;

    private static final int INITIAL_BORROWER_COUNT = 8;
    private static final int INITIAL_ITEM_COUNT = 12;
    private static final int INITIAL_LOAN_COUNT = 6;

    private static final int BORROWER_NO_FOR_NEW_LOAN = 8;
    private static final int ITEM_NO_FOR_NEW_LOAN = 5;

    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
    	dataHelper.prepareData();

        borrowerDao = new BorrowerDAOMemory();
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();

        borrowerCategoryDao = new BorrowerCategoryDAOMemory();
        bookDao = new BookDAOMemory();
        publisherDao = new PublisherDAOMemory();
        authorDao = new AuthorDAOMemory();

        countryDao = new CountryDAOMemory();
    }

    @Test
    public void countriesSizeCheck() {
        Assert.assertEquals(205, countryDao.getCountries().size());
    }

    @Test
    public void findExistingLoan() {
        Assert.assertNotNull(loanDao.find(1));
    }

    /**
     * Αναζήτηση δανειζομένου που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingBorrower() {
        Borrower borrower = borrowerDao.find(8);
        Assert.assertEquals("Νίκος", borrower.getFirstName());                
    }
    
    /**
     * Αναζήτηση δανειζομένου που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingBorrower() {
        Borrower borrower = borrowerDao.find(4711);
        Assert.assertNull(borrower);
    }

    @Test
    public void findNonExistingAuthor() {
        Assert.assertNull(authorDao.find(4711));
    }

    @Test
    public void findNonExistingBook() {
        Assert.assertNull(bookDao.find(4711));
    }

    @Test
    public void findNonExistingBorrowerCategory() {
        Assert.assertNull(borrowerCategoryDao.find(4711));
    }

    @Test
    public void findNonExistingLoan() {
        Assert.assertNull(loanDao.find(4711));
    }

    @Test
    public void findNonExistingPublisher() {
        Assert.assertNull(publisherDao.find(4711));
    }

    /**
     * Εύρεση καταλόγου δανειζομένων
     */
    @Test
    public void listAllBorrowers() {        
        List<Borrower> allBorrowers = borrowerDao.findAll();
        Assert.assertEquals(INITIAL_BORROWER_COUNT, allBorrowers.size());
    }
    
    /**
     * Αποθήκευση δανειζομένου
     */
    @Test
    public void saveBorrower() {        
        Borrower borrower = new Borrower(borrowerDao.nextId(), "Νίκος", "Σαραντινός",
                new Address("Πανεπιστημίου", "27", "Αθήνα", new ZipCode("1229"), "Ελλάδα"),
                new EmailAddress("nsar@aueb.gr"), new TelephoneNumber("2108203292"), borrowerCategoryDao.find(5/*"Καθηγητής"*/));

        borrowerDao.save(borrower);
        Assert.assertEquals(INITIAL_BORROWER_COUNT + 1, borrowerDao.findAll().size());
        Assert.assertNotNull(borrowerDao.find(borrower.getBorrowerNo()));
        Assert.assertTrue(borrowerDao.findAll().contains(borrower));
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
        Assert.assertEquals(INITIAL_BORROWER_COUNT - 1, allBorrowers.size());
        Assert.assertNull(borrowerDao.find(borrower.getBorrowerNo()));
    }
    
    /**
     * Αναζήτηση αντιτύπου που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingItem() {
        String EXPECTED_ISBN_FROM_ITEM = "2";
        
        Item item = itemDao.find(1);
        Assert.assertEquals(EXPECTED_ISBN_FROM_ITEM, item.getBook().getIsbn().getValue());
    }
    
    
    /**
     * Αναζήτηση αντιτύπου που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingItem() {
        Item item = itemDao.find(4711);
        Assert.assertNull(item);
    }
    
    /**
     * Κατάλογος όλων των αντιτύπων
     */
    @Test
    public void listAllItems() {
        List<Item> allItems = itemDao.findAll();        
        Assert.assertEquals(INITIAL_ITEM_COUNT, allItems.size());        
    }
    
    /**
     * Αποθήκευση αντιτύπου
     */
    @Test
    public void saveItem() {
        Book book = new Book(bookDao.nextId(), "Don Quixote 2", new ISBN("1234567"), "978-3-16-20", 2016, publisherDao.find(2));
        Item item = new Item(itemDao.nextId());
        item.setBook(book);
        itemDao.save(item);
        List<Item> allItems = itemDao.findAll();
        Assert.assertEquals(INITIAL_ITEM_COUNT + 1, allItems.size());
        Assert.assertNotNull(itemDao.find(item.getItemNumber()));
        Assert.assertTrue(allItems.contains(item));
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
        Assert.assertEquals(INITIAL_ITEM_COUNT - 1, allItems.size());
        Assert.assertNull(itemDao.find(item.getItemNumber()));
        Assert.assertFalse(allItems.contains(item));        
    }
    
    
    /**
     * Αποθήκευση δανεισμού    
     */
    @Test
    public void saveLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        List<Loan> allLoans = loanDao.findAll();
        Assert.assertEquals(INITIAL_LOAN_COUNT + 1, allLoans.size());
        Assert.assertNotNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));        
        
    }

    /**
     * Αναζήτηση δανεισμού σε εκκρεμότητα (χωρίς την επιστροφή)
     */
    @Test
    public void findExistingPendingLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        Assert.assertNotNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));        
    }
    
    /**
     * Αναζήτηση δανεισμού σε εκκρεμότητα όταν αυτός δεν υπάρχει
     */
    @Test
    public void findNonExistingPendingLoan() {
        Loan loan = CreateNewLoan();
        loanDao.save(loan);
        Assert.assertNull(loanDao.findPending(4711));
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
        Assert.assertNull(loanDao.findPending(ITEM_NO_FOR_NEW_LOAN));
    }

    private Loan CreateNewLoan() {
        Borrower borrower = borrowerDao.find(BORROWER_NO_FOR_NEW_LOAN);
        Item item = itemDao.find(ITEM_NO_FOR_NEW_LOAN);
        Loan loan = item.borrow(borrower, loanDao.nextId());
        return loan;
    }
}
