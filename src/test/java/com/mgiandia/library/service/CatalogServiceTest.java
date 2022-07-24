package com.mgiandia.library.service;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;

@QuarkusTest
public class CatalogServiceTest  {
	
	@Inject
	CatalogService service;

	@Test
	@TestTransaction 
	public void testFindAllBooks() {
		List<Book> books = service.findAllBooks();
		assertNotNull(books);
		assertEquals(3, books.size());
	}

	@Test
	@TestTransaction 
	public void testFindBooksByAuthor() {

		 List<Book> books = service.findBooksByAuthor("Fowler");
		 assertNotNull(books);
		 assertEquals(2, books.size());

	}

	@Test
	@TestTransaction 
	public void testFindBookById() {
		 List<Book> allBooks = service.findAllBooks();
		 Book book = service.findBookById(allBooks.get(0).getId());
		 assertNotNull( book, "Expected non null book");
	}

	@Test
	@TestTransaction 
	public void testFindBookByTitle() {
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
