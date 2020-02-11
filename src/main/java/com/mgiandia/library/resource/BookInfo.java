package com.mgiandia.library.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.ISBN;
import com.mgiandia.library.domain.Publisher;

/**
 * Value object for transferring book data over the wire ...
 * 
 * @author Vassilis Zafeiris
 *
 */
@XmlRootElement
public class BookInfo {

	private Integer id;

	private String isbn;

	private String title;

	private String publication;

	private int publicationyear;

	private int publisherId;

	public BookInfo() {

	}

	public BookInfo(int id, String isbn, String title, String publication, int publicationyear, int publisherId) {
		this(isbn, title, publication, publicationyear, publisherId);
		this.id = id;

	}

	public BookInfo(String isbn, String title, String publication, int publicationyear, int publisherId) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.publication = publication;
		this.publicationyear = publicationyear;
		this.publisherId = publisherId;
	}

	public BookInfo(Book book) {
		id = book.getId();
		isbn = book.getIsbn().getValue();
		title = book.getTitle();
		publication = book.getPublication();
		publicationyear = book.getPublicationYear();
		publisherId = book.getPublisher().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public int getPublicationYear() {
		return publicationyear;
	}

	public void setPublicationYear(int publicationyear) {
		this.publicationyear = publicationyear;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public static BookInfo wrap(Book b) {
		return new BookInfo(b);
	}

	public static List<BookInfo> wrap(List<Book> books) {

		List<BookInfo> bookInfoList = new ArrayList<>();

		for (Book b : books) {
			bookInfoList.add(new BookInfo(b));
		}

		return bookInfoList;

	}

	public Book getBook(EntityManager em) {

		Book book = null;

		if (id != null) {
			book = em.find(Book.class, id);
		} else {
			book = new Book();
		}

		book.setTitle(title);
		book.setPublication(publication);
		book.setPublicationYear(publicationyear);

		if (book.getIsbn() == null || !book.getIsbn().getValue().equals(isbn)) {
			book.setIsbn(new ISBN(isbn));
		}

		Publisher publisher = em.getReference(Publisher.class, publisherId);

		book.setPublisher(publisher);

		return book;
	}
}
