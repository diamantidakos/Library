package com.mgiandia.library.presenter;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookSearchPresenter {

    public Set<Book> searchBooks(String title, String authorName){

        BookDAOMemory bookDAOMemory = new BookDAOMemory();
        List<Book> resultA = bookDAOMemory.findByTitle(title);
        List<Book> resultB = bookDAOMemory.findByAuthorName(authorName);

        Set<Book> result = new HashSet<>();
        result.addAll(resultA);
        result.addAll(resultB);
        return result;

    }

}
