package com.mgiandia.library.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;

 

public class AuthorTest {
    
    @Test
    public void addNullForBook() {
        Author author = new Author();                    
        author.addBook(null);
        Assertions.assertEquals(0,author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);        
    }
    
    @Test
    public void addBook() {
        Author author = new Author();
        Book book = new Book();
        author.addBook(book);
        Assertions.assertEquals(1, author.getBooks().size());
        Assertions.assertTrue(author.getBooks().contains(book));
        bookBidirectionalAssociationInvariant(author);        
    }
    
    
    @Test
    public void removeNullForBook() {
        Author author = new Author();
        Book book = new Book();        
        author.addBook(book);
        author.removeBook(null);
        Assertions.assertEquals(1, author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);
    }
    
    @Test
    public void removeBook() {
        Author author = new Author();
        Book book = new Book();
        author.addBook(book);
        bookBidirectionalAssociationInvariant(author);
        author.removeBook(book);
        Assertions.assertEquals(0, author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);
    }
    
    
    
    private void bookBidirectionalAssociationInvariant(Author author) {
        for(Book book : author.getBooks()) {
        	Assertions.assertTrue(book.getAuthors().contains(author));            
        }
    }
}
