package com.mgiandia.library.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;

public class CatalogServiceTest extends LibraryServiceTest {

	@Test
	public void testFindAllBooks() {
		
		CatalogService service = new CatalogService(em);
		List<Book> books = service.findAllBooks();

		assertNotNull(books);
		assertEquals(3, books.size());
	}

	@Test
	public void testFindBooksByAuthor() {

		 CatalogService service = new CatalogService(em);
		 List<Book> books = service.findBooksByAuthor("Fowler");
		
		 assertNotNull(books);
		 assertEquals(1, books.size());

	}

	@Test
	public void testFindBookById() {

		 CatalogService service = new CatalogService(em);
		 List<Book> allBooks = service.findAllBooks();
		
		 Book book = service.findBookById(allBooks.get(0).getId());
		
		 assertNotNull( book, "Expected non null book");

	}

	@Test
	public void testFindBookByTitle() {

		CatalogService service = new CatalogService(em);
		List<Book> books = service.findBooksByTitle("%UML%");

		assertNotNull(books);
		assertEquals(1, books.size());
		assertTrue(books.get(0).getAuthors().size() == 1);

		for (Author aut : books.get(0).getAuthors()) {
			assertNotNull(aut);
			assertEquals(aut.getFirstName(), "Martin");
		}

	}

}
