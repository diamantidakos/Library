package com.mgiandia.library.resource;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.domain.Publisher;

import static com.mgiandia.library.resource.LibraryUri.*; 

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

		List<Publisher> publishers = listPublishers();

		Assert.assertEquals(1, publishers.size());

		Publisher p = publishers.get(0);

		Response response = target(BOOKS).request().post(Entity
				.entity(new BookInfo("444", "Another UML book", "Wiley", 2016, p.getId()), MediaType.APPLICATION_JSON));

		Assert.assertEquals(201, response.getStatus());

		// System.out.println("Status:" + response.getStatus());
		System.out.println("Resource URI:" + response.getHeaderString("Location"));

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
