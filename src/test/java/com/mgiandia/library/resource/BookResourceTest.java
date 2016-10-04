package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BOOKS;
import static com.mgiandia.library.resource.LibraryUri.BOOK_SEARCH;
import static com.mgiandia.library.resource.LibraryUri.bookIdUri;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Publisher;

public class BookResourceTest extends LibraryResourceTest {

	@Override
	protected Application configure() {
		/*
		 * 
		 */
		return new ResourceConfig(BookResource.class, DebugExceptionMapper.class);
	}

	@Test
	public void testListBookById() {

		// get all books
		List<BookInfo> books = target(BOOKS).request().get(new GenericType<List<BookInfo>>() {
		});

		String firstBookId = Integer.toString(books.get(0).getId());

		BookInfo book = target(bookIdUri(firstBookId)).request().get(BookInfo.class);
		Assert.assertNotNull(book);
		Assert.assertEquals("The Unified Modeling Language User Guide", book.getTitle());
	}

	@Test
	public void testListAllBooks() {

		List<BookInfo> books = target(BOOKS).request().get(new GenericType<List<BookInfo>>() {
		});
		Assert.assertEquals(3, books.size());
	}

	@Test
	public void testCreateNewBook() {

		// Find a publisher
		List<Publisher> publishers = listPublishers();
		Assert.assertEquals(1, publishers.size());
		Publisher p = publishers.get(0);

		// Create a book info object and submit
		BookInfo bookInfo = new BookInfo("444", "Another UML book", "Wiley", 2016, p.getId());

		Response response = target(BOOKS).request().post(Entity.entity(bookInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(201, response.getStatus());
		List<Book> foundBooks = findBooksByTitle("Another UML book");
		Assert.assertEquals(1, foundBooks.size());

	}

	@Test
	public void testUpdateBook() {

		// Find a book and update its title
		List<Book> books = findBooksByTitle("UML");
		Assert.assertEquals(1, books.size());
		BookInfo bookInfo = BookInfo.wrap(books.get(0));
		bookInfo.setTitle("Another UML book");

		// Submit the updated representation
		Response response = target(bookIdUri(Integer.toString(bookInfo.getId()))).request()
				.put(Entity.entity(bookInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		List<Book> foundBooks = findBooksByTitle("Another UML book");
		Assert.assertEquals(1, foundBooks.size());

	}

	@Test
	public void testDeleteExistingBook() {
		// Find a book and update its title
		List<Book> books = findBooksByTitle("UML");
		Assert.assertEquals(1, books.size());
		Book book = books.get(0);

		// Submit the updated representation
		Response response = target(bookIdUri(Integer.toString(book.getId()))).request().delete();

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		List<Book> foundBooks = findBooksByTitle("UML");
		Assert.assertEquals(0, foundBooks.size());

	}

	@Test
	public void testDeleteNonExistingBook() {

		Response response = target(bookIdUri(Integer.toString(Integer.MAX_VALUE))).request().delete();

		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	@Test
	public void testSearchBookByTitle() {
		System.out.println(LibraryUri.bookSearchUri("UML"));
		List<BookInfo> books = target(BOOK_SEARCH).queryParam("title", "UML").request()
				.get(new GenericType<List<BookInfo>>() {
				});

		Assert.assertEquals(1, books.size());
	}

}
