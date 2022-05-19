package com.mgiandia.library.memorydao;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.BorrowerCategoryDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.CountryDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.LoanDAO;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.dao.ReservationRequestDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.domain.Publisher;

public class MemoryInitializer extends Initializer {

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    @Override
    protected void eraseData() {

        for(Author author : getAuthorDAO().findAll()) {
            getAuthorDAO().delete(author);
        }

        for(Book book : getBookDAO().findAll()) {
            getBookDAO().delete(book);
        }

        for(BorrowerCategory borrowerCategory : getBorrowerCategoryDAO().findAll()) {
            getBorrowerCategoryDAO().delete(borrowerCategory);
        }

        for(Borrower borrower : getBorrowerDAO().findAll()) {
            getBorrowerDAO().delete(borrower);
        }

        for(Item item : getItemDAO().findAll()) {
            getItemDAO().delete(item);
        }

        for(Loan loan : getLoanDAO().findAll()) {
            getLoanDAO().delete(loan);
        }

        for(Publisher publisher : getPublisherDAO().findAll()) {
            getPublisherDAO().delete(publisher);
        }
    }

    /**
     * Επιστρέφει το DAO των συγγραφέων.
     * @return Το DAO των συγγραφέων
     */
    @Override
    public AuthorDAO getAuthorDAO()
    {
        return new AuthorDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των βιβλίων.
     * @return Το DAO των βιβλίων
     */
    @Override
    public BookDAO getBookDAO()
    {
        return new BookDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των κατηγοριών δανειζομένων.
     * @return Το DAO των κατηγοριών δανειζομένων
     */
    @Override
    public BorrowerCategoryDAO getBorrowerCategoryDAO()
    {
        return new BorrowerCategoryDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των δανειζομένων.
     * @return Το DAO των δανειζομένων
     */
    @Override
    public BorrowerDAO getBorrowerDAO() {
        return new BorrowerDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των αντιτύπων.
     * @return Το DAO των αντιτύπων
     */
    @Override
    public ItemDAO getItemDAO() {
        return new ItemDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των δανείων.
     * @return Το DAO των δανείων
     */
    @Override
    public LoanDAO getLoanDAO() {
        return new LoanDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των εκδοτικών οίκων.
     * @return Το DAO των εκδοτικών οίκων
     */
    @Override
    public PublisherDAO getPublisherDAO()
    {
        return new PublisherDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των χωρών.
     * @return Το DAO των χωρών
     */
    @Override
    public CountryDAO getCountryDAO()
    {
        return new CountryDAOMemory();
    }

    public ReservationRequestDAO getReservationRequestDAO(){
        return new ReservationRequestDAOMemory();
    }

}