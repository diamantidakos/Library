package com.mgiandia.library.domain;


import org.junit.*;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;



public class BookTest {

    Book book;
    Item item;
    
    @Before
    public void setUp() {
        book = new Book();
        item = new Item();
        item.setItemNumber(1);
        item.available();
        book.addItem(item);
    }
    
    
    
    @Test
    public void succefulSetUp() {
        itemBidirectionalAssociationInvariant(book);
        expectedItemsSize(book,1);
        Assert.assertEquals(ItemState.AVAILABLE, item.getState());
        successfullAdditionofItem(book,item);
    }
    
    
    @Test
    public void addNullAsItem() {
        expectedItemsSize(book,1);
        book.addItem(null);
        expectedItemsSize(book,1);
        itemBidirectionalAssociationInvariant(book);
    }
        
    @Test
    public void addNullAsAuthor() {
        book.addAuthor(null);
        Assert.assertEquals(0, book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void addItem() {        
        expectedItemsSize(book,1);        
        item = new Item();
        item.setItemNumber(2);
        book.addItem(item);
        successfullAdditionofItem(book,item);
        expectedItemsSize(book,2);
        itemBidirectionalAssociationInvariant(book);
    }

    @Test
    public void addAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        Assert.assertEquals(1,book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void removeNullAsItem() {
        expectedItemsSize(book,1);
        book.removeItem(null);
        expectedItemsSize(book,1);
        itemBidirectionalAssociationInvariant(book);        
    }
    
    @Test
    public void removeNullAsAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        book.removeAuthor(null);
        Assert.assertEquals(1, book.getAuthors().size());
        authorBidirectionalAssociationInvariant(book);
    }
    
    
    @Test
    public void removeItem() {
        itemBidirectionalAssociationInvariant(book);
        expectedItemsSize(book,1);
        book.removeItem(item);
        successfullRemovalofItem(book,item);
        expectedItemsSize(book,0);        
        itemBidirectionalAssociationInvariant(book);
    }

    @Test
    public void removeAuthor() {
        Author author = new Author();
        book.addAuthor(author);
        authorBidirectionalAssociationInvariant(book);
        Assert.assertEquals(1, book.getAuthors().size());
        book.removeAuthor(author);
        authorBidirectionalAssociationInvariant(book);
        Assert.assertEquals(0, book.getAuthors().size());
    }
    
    
    @Test
    public void addingSameItemIntoTwoBooks() {
        Book book2 = new Book();
        book2.addItem(item);
        successfullAdditionofItem(book2,item);
        itemBidirectionalAssociationInvariant(book);
        itemBidirectionalAssociationInvariant(book2);        
    }
    
    
    private void itemBidirectionalAssociationInvariant(Book book) {
        for(Item item : book.getItems()) {
            Assert.assertSame(book, item.getBook());
        }
    }
    
    private void successfullAdditionofItem(Book book, Item item){
        Assert.assertTrue(book.getItems().contains(item));
        Assert.assertSame(book, item.getBook());
    }
    
    private void successfullRemovalofItem(Book book, Item item) {
        Assert.assertFalse(book.getItems().contains(item));
        Assert.assertNull(item.getBook());
    }
    
    private void expectedItemsSize(Book book, int expectedSize) {
        Assert.assertEquals(expectedSize, book.getItems().size());
    }
    
    private void authorBidirectionalAssociationInvariant(Book book) {
        for(Author author : book.getAuthors()) {
            Assert.assertTrue(author.getBooks().contains(book));            
        }    
    }
}
