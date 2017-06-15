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
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.domain.Publisher;

public class MemoryInitializer extends Initializer {

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

    @Override
    public AuthorDAO getAuthorDAO()
    {
        return new AuthorDAOMemory();
    }

    @Override
    public BookDAO getBookDAO()
    {
        return new BookDAOMemory();
    }

    @Override
    public BorrowerCategoryDAO getBorrowerCategoryDAO()
    {
        return new BorrowerCategoryDAOMemory();
    }

    @Override
    public BorrowerDAO getBorrowerDAO() {
        return new BorrowerDAOMemory();
    }

    @Override
    public ItemDAO getItemDAO() {
        return new ItemDAOMemory();
    }

    @Override
    public LoanDAO getLoanDAO() {
        return new LoanDAOMemory();
    }

    @Override
    public PublisherDAO getPublisherDAO()
    {
        return new PublisherDAOMemory();
    }

    @Override
    public CountryDAO getCountryDAO()
    {
        return new CountryDAOMemory();
    }
}