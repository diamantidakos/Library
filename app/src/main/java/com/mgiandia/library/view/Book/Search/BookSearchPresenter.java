package com.mgiandia.library.view.Book.Search;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.domain.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookSearchPresenter {
    private BookDAO bookDAO;
    private BookSearchView view;
    private Set<Book> searchResult = new HashSet<>();

    public BookSearchPresenter() {
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void setView(BookSearchView view) {
        this.view = view;
    }

    public void search(String titleCriterion, String authorCriterion) {

        searchResult.clear();
        if (isEmpty(titleCriterion) && isEmpty(authorCriterion)){
            searchResult.addAll(bookDAO.findAll());
        }
        Set<Book> titleSearchResult = new HashSet<>();
        Set<Book> authorSearchResult = new HashSet<>();
        if (!isEmpty(titleCriterion)){
            titleSearchResult.addAll(bookDAO.findByTitle(titleCriterion));
        }
        if (!isEmpty(authorCriterion)){
            authorSearchResult.addAll(bookDAO.findByAuthorName(authorCriterion));
        }
        if (!isEmpty(titleCriterion) && !isEmpty(authorCriterion)){
            searchResult.addAll(titleSearchResult);
            searchResult.retainAll(authorSearchResult);
            return;
        }

        searchResult.addAll(titleSearchResult);
        searchResult.addAll(authorSearchResult);

    }

    public Set<Book> getSearchResult() {
        return searchResult;
    }

    private boolean isEmpty(String searchTerm){
        return searchTerm == null || searchTerm.isEmpty();
    }
}
