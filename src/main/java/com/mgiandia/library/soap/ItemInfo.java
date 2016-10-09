package com.mgiandia.library.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Item;

@XmlType(name="ItemInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ItemInfo {
    private int itemNo;
    private String status;
    private String title;
    private String isbn;
    private String authors;
    private String publication;
    private int year;

    public ItemInfo() {}
    
    public ItemInfo(Item item) {
        itemNo = item.getItemNumber();
        status = item.getState().toString();
        title  = item.getBook().getTitle();
        isbn = item.getBook().getIsbn().getValue();
        authors = authors2String(item);
        publication = item.getBook().getPublication();
        year = item.getBook().getPublicationYear();
    }
    
    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getAuthors() {
        return authors;
    }
    
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublication() {
        return publication;
    }
    
    public void setPublication(String publication) {
        this.publication = publication;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    public String authors2String(Item item) {
        String result = "";
        for(Author author : item.getBook().getAuthors()) {
            result += author.getLastName() + 
                " " + author.getFirstName() + ", ";
            
        }
        return result;
    }
    
    
}
