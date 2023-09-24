package com.mgiandia.library.domain;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class BookTest {

    Book book;
    Item item;
    
    @BeforeEach
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
        Assertions.assertEquals(ItemState.AVAILABLE, item.getState());
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
        Assertions.assertEquals(0, book.getAuthors().size());
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
        Assertions.assertEquals(1,book.getAuthors().size());
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
        Assertions.assertEquals(1, book.getAuthors().size());
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
        Assertions.assertEquals(1, book.getAuthors().size());
        book.removeAuthor(author);
        authorBidirectionalAssociationInvariant(book);
        Assertions.assertEquals(0, book.getAuthors().size());
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
        	Assertions.assertSame(book, item.getBook());
        }
    }
    
    private void successfullAdditionofItem(Book book, Item item){
    	Assertions.assertTrue(book.getItems().contains(item));
    	Assertions.assertSame(book, item.getBook());
    }
    
    private void successfullRemovalofItem(Book book, Item item) {
    	Assertions.assertFalse(book.getItems().contains(item));
    	Assertions.assertNull(item.getBook());
    }
    
    private void expectedItemsSize(Book book, int expectedSize) {
    	Assertions.assertEquals(expectedSize, book.getItems().size());
    }
    
    private void authorBidirectionalAssociationInvariant(Book book) {
        for(Author author : book.getAuthors()) {
        	Assertions.assertTrue(author.getBooks().contains(book));            
        }    
    }
}
