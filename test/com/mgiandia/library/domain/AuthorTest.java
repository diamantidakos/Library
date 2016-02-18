package com.mgiandia.library.domain;

import org.junit.*;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;

 

public class AuthorTest {
    
    @Test
    public void addNullForBook() {
        Author author = new Author();                    
        author.addBook(null);
        Assert.assertEquals(0,author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);        
    }
    
    @Test
    public void addBook() {
        Author author = new Author();
        Book book = new Book();
        author.addBook(book);
        Assert.assertEquals(1, author.getBooks().size());
        Assert.assertTrue(author.getBooks().contains(book));
        bookBidirectionalAssociationInvariant(author);        
    }
    
    
    @Test
    public void removeNullForBook() {
        Author author = new Author();
        Book book = new Book();        
        author.addBook(book);
        author.removeBook(null);
        Assert.assertEquals(1, author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);
    }
    
    @Test
    public void removeBook() {
        Author author = new Author();
        Book book = new Book();
        author.addBook(book);
        bookBidirectionalAssociationInvariant(author);
        author.removeBook(book);
        Assert.assertEquals(0, author.getBooks().size());
        bookBidirectionalAssociationInvariant(author);
    }
    
    
    
    private void bookBidirectionalAssociationInvariant(Author author) {
        for(Book book : author.getBooks()) {
            Assert.assertTrue(book.getAuthors().contains(author));            
        }
    }
}
