package com.mgiandia.library.dao;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;
import com.mgiandia.library.contacts.ZipCode;
import com.mgiandia.library.domain.*;
import com.mgiandia.library.service.LoanService;
import com.mgiandia.library.util.Money;

/**
 * Κλάση που αναλαμβάνει να αρχικοποιήσει τα δεδομένα της βάσης δεδομένων<p>
 * Βοηθητική κλάση που παρέχει δεδομένα για τα παραδείγματα και τις δοκιμασίες ελέγχου<p>
 * Βιβλία
 * <p>
 * Booch, G.,  Rumbaugh, J., Jacobson I., The Unified Modeling Language User Guide , 2nd ed., Addison Wesley, 2005
 * <p>
 * Fowler, M., UML Distilled, 3rd ed., Addison-Wesley, 2004
 * <p>
 * Fowler, M., Refactoring: Improving the Design of Existing Code. Addison-Wesley, 1999
 * <p>
 * Αντίτυπα
 * <p>
 * 1 - The Unified Modeling Language User Guide
 * <p>
 * 2 -  UML Distilled
 * <p>
 * 3 - Refactoring: Improving the Design of Existing Code
 * <p>
 * 4 - The Unified Modeling Language User Guide
 * <p>
 * 5 - UML Distilled
 * <p>
 * Δανειζόμενοι
 * <p>
 * 1, Μανόλης Γιακουμάκης, Καθηγητής
 * <p>
 * 2, Νίκος Διαμαντίδης, Φοιτητής
 * <p>
 *@author Νίκος Διαμαντίδης
 *
 */

public abstract class Initializer
{
    public static final int GIAKOUMAKIS_ID = 2;
    public static final int DIAMANTIDIS_ID = 8;

    public static final int UML_USER_GUIDE_ID1 = 3;
    public static final int UML_DISTILLED_ID1 = 1;
    public static final int UML_REFACTORING_ID = 5;
    public static final int UML_USER_GUIDE_ID2 = 4;
    public static final int UML_DISTILLED_ID2 = 2;

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected abstract void eraseData();

    /**
     * Προσθέτει ένα αντίτυπο ενός συγκεκριμένου βιβλίου.
     * @param book Το βιβλίο
     * @return Το αντίτυπο που προστέθηκε
     */
    private Item addOneBookItem(Book book)
    {
        ItemDAO item = getItemDAO();
        Item itemTmp = new Item(item.nextId());
        itemTmp.setBook(book);
        item.save(itemTmp);
        return itemTmp;
    }

    /**
     * Αλλάζει την κατάσταση ενός συγκεκριμένου αντιτύπου σε καινούργιο.
     * @param item Το αντίτυπο
     * @return Το αντίτυπο
     */
    public Item makeAvailable(Item item)
    {
        if(item.getState() == ItemState.NEW)
            item.available();

        return item;
    }

    /**
     * Εισάγει τα δοκιμαστικά δεδομένα.
     */
    public void prepareData()
    {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        PublisherDAO publisherDAO = getPublisherDAO();

        publisherDAO.save(new Publisher(publisherDAO.nextId(), "McGraw-Hill Education", new Address("Αιόλου", "10", "Αθήνα",
                new ZipCode("100"), "Ελλάδα"), new EmailAddress("akr.agam@gmail.com"), new TelephoneNumber("2101070741")));

        publisherDAO.save(new Publisher(publisherDAO.nextId(), "Addison Wesley", new Address("Ακαδημίας", "22", "Αθήνα",
                new ZipCode("129"), "Ελλάδα"), new EmailAddress("add.wes@gmail.com"), new TelephoneNumber("2102070991")));


        AuthorDAO authorDAO = getAuthorDAO();

        authorDAO.save(new Author(authorDAO.nextId(), "Ευάγγελος", "Αβέρωφ"));
        authorDAO.save(new Author(authorDAO.nextId(), "Χρήστος", "Βυζάντιος"));
        authorDAO.save(new Author(authorDAO.nextId(), "Απόστολος", "Γεωργιάδης"));
        authorDAO.save(new Author(authorDAO.nextId(), "Martin", "Fowler"));
        authorDAO.save(new Author(authorDAO.nextId(), "John", "Doe"));


        BorrowerCategoryDAO borrowerCategoryDao = getBorrowerCategoryDAO();

        borrowerCategoryDao.save(new BorrowerCategory(borrowerCategoryDao.nextId(), "Προπτυχιακός Φοιτητής", 7 , 2, Money.euros(10)));
        borrowerCategoryDao.save(new BorrowerCategory(borrowerCategoryDao.nextId(), "Μεταπτυχιακός Φοιτητής", 14 , 3, Money.euros(7)));
        borrowerCategoryDao.save(new BorrowerCategory(borrowerCategoryDao.nextId(), "Διδακτορικός Φοιτητής", 21 , 4, Money.euros(5)));
        borrowerCategoryDao.save(new BorrowerCategory(borrowerCategoryDao.nextId(), "Μέλος Δ.Ε.Π.", 21 , 4, Money.euros(5)));
        borrowerCategoryDao.save(new BorrowerCategory(borrowerCategoryDao.nextId(), "Καθηγητής", 180 , 6, Money.euros(0)));


        BorrowerDAO borrowerDao = getBorrowerDAO();
        //all country names should be from our list, see string.xml

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Αγαμέμνων", "Ακρίδας",
                new Address("Αιόλου", "10", "Αθήνα", new ZipCode("100"), "Ελλάδα"),
                new EmailAddress("akr.agam@gmail.com"), new TelephoneNumber("2101070741"), borrowerCategoryDao.find(1/*"Προπτυχιακός Φοιτητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Μανόλης", "Γιακουμάκης",
                new Address("Αιόλου", "11", "Αθήνα", new ZipCode("119"), "Ελλάδα"),
                new EmailAddress("mgia@aueb.gr"), new TelephoneNumber("2108203292"), borrowerCategoryDao.find(5/*"Καθηγητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Βασίλης", "Δραγούμης",
                new Address("Ερμού", "100", "Αθήνα", new ZipCode("150"), "Ελλάδα"),
                new EmailAddress("bal.drag@gmail.com"), new TelephoneNumber("2101070911"), borrowerCategoryDao.find(3/*"Διδακτορικός Φοιτητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Άρτεμις", "Λύτρου",
                new Address("Ερμού", "101", "Αθήνα", new ZipCode("150"), "Ελλάδα"),
                new EmailAddress("art.lutou@gmail.com"), new TelephoneNumber("2102071912"), borrowerCategoryDao.find(3/*"Διδακτορικός Φοιτητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Γερακίνα", "Λούπη",
                new Address("Πανεπιστημίου", "122", "Αθήνα", new ZipCode("170"), "Ελλάδα"),
                new EmailAddress("ger.louph@gmail.com"), new TelephoneNumber("2101271123"), borrowerCategoryDao.find(3/*"Διδακτορικός Φοιτητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Mario", "Cox",
                new Address("Glenwood St. NE", "1807", "Palm Bay FL", new ZipCode("32907"), "Ηνωμένο Βασίλειο"),
                new EmailAddress("mario.lee@gmail.com"), new TelephoneNumber("5417543010"), borrowerCategoryDao.find(5/*"Καθηγητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Αντρέας", "Βυζάντιος",
                new Address("Πανεπιστημίου", "123", "Αθήνα", new ZipCode("170"), "Ελλάδα"),
                new EmailAddress("adre.bizant@gmail.com"), new TelephoneNumber("2101271131"), borrowerCategoryDao.find(5/*"Καθηγητής"*/)));

        borrowerDao.save(new Borrower(borrowerDao.nextId(), "Νίκος", "Διαμαντίδης",
                new Address("Πανεπιστημίου", "27", "Αθήνα", new ZipCode("1229"), "Ελλάδα"),
                new EmailAddress("nad@aueb.gr"), new TelephoneNumber("2108203292"), borrowerCategoryDao.find(5/*"Προπτυχιακός Φοιτητής"*/)));

        BookDAO bookDao = getBookDAO();

        bookDao.save(new Book(bookDao.nextId(), "Don Quixote", new ISBN("123456"), "978-3-16", 2012, publisherDAO.find(2)));
        bookDao.save(new Book(bookDao.nextId(), "The Odyssey", new ISBN("738912"), "123-4-56", 2000, publisherDAO.find(1)));

        bookDao.save(new Book(bookDao.nextId(), "UML Distilled", new ISBN("2"), "654-4567", 2004, publisherDAO.find(2)));
        bookDao.save(new Book(bookDao.nextId(), "The UML User Guide", new ISBN("1"), "456-6546", 2005, publisherDAO.find(2)));
        bookDao.save(new Book(bookDao.nextId(), "Refactoring", new ISBN("3"), "554-34534", 1999, publisherDAO.find(2)));

        addOneBookItem(bookDao.find(3)).available();
        addOneBookItem(bookDao.find(3)).available();

        addOneBookItem(bookDao.find(4)).available();
        addOneBookItem(bookDao.find(4)).available();

        addOneBookItem(bookDao.find(5)).available();


        //add a copy per book
        addOneBookItem(bookDao.find(1));
        addOneBookItem(bookDao.find(2));

        //publishers are auto informed about the added books

        //now, link authors with books
        authorDAO.find(4).addBook(bookDao.find(3));
        authorDAO.find(4).addBook(bookDao.find(4));
        authorDAO.find(4).addBook(bookDao.find(5));

        authorDAO.find(1).addBook(bookDao.find(1));
        authorDAO.find(2).addBook(bookDao.find(1));
        authorDAO.find(3).addBook(bookDao.find(1));

        authorDAO.find(3).addBook(bookDao.find(2));
        authorDAO.find(4).addBook(bookDao.find(2));

        getItemDAO().find(7).available();
        LoanService service = new LoanService();
        service.findBorrower(1);
        service.borrow(7, getLoanDAO().nextId());

        //some loans
        LoanDAO loans = getLoanDAO();
        loans.save(makeAvailable(addOneBookItem(bookDao.find(2))).borrow(borrowerDao.find(8), getLoanDAO().nextId()));
        loans.save(makeAvailable(addOneBookItem(bookDao.find(1))).borrow(borrowerDao.find(8), getLoanDAO().nextId()));
        loans.save(makeAvailable(addOneBookItem(bookDao.find(2))).borrow(borrowerDao.find(5), getLoanDAO().nextId()));
        loans.save(makeAvailable(addOneBookItem(bookDao.find(1))).borrow(borrowerDao.find(3), getLoanDAO().nextId()));
        loans.save(makeAvailable(addOneBookItem(bookDao.find(1))).borrow(borrowerDao.find(1), getLoanDAO().nextId()));
    }

    /**
     * Επιστρέφει το DAO των συγγραφέων.
     * @return Το DAO των συγγραφέων
     */
    public abstract AuthorDAO getAuthorDAO();

    /**
     * Επιστρέφει το DAO των βιβλίων.
     * @return Το DAO των βιβλίων
     */
    public abstract BookDAO getBookDAO();

    /**
     * Επιστρέφει το DAO των κατηγοριών δανειζομένων.
     * @return Το DAO των κατηγοριών δανειζομένων
     */
    public abstract BorrowerCategoryDAO getBorrowerCategoryDAO();

    /**
     * Επιστρέφει το DAO των δανειζομένων.
     * @return Το DAO των δανειζομένων
     */
    public abstract BorrowerDAO getBorrowerDAO();

    /**
     * Επιστρέφει το DAO των αντιτύπων.
     * @return Το DAO των αντιτύπων
     */
    public abstract ItemDAO getItemDAO();

    /**
     * Επιστρέφει το DAO των δανείων.
     * @return Το DAO των δανείων
     */
    public abstract LoanDAO getLoanDAO();

    /**
     * Επιστρέφει το DAO των εκδοτικών οίκων.
     * @return Το DAO των εκδοτικών οίκων
     */
    public abstract PublisherDAO getPublisherDAO();

    /**
     * Επιστρέφει το DAO των χωρών.
     * @return Το DAO των χωρών
     */
    public abstract CountryDAO getCountryDAO();

    public abstract ReservationRequestDAO getReservationRequestDAO();
}
