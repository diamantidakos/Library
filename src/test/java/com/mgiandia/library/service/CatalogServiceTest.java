package com.mgiandia.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;

public class CatalogServiceTest {

	Initializer dataHelper;
	
	EntityManager em;
	
	public void setUp() {
		dataHelper.prepareData();
	}


	@Before
	public void setUpJpa() {
		dataHelper = new Initializer();
		setUp();
		
		em = JPAUtil.getCurrentEntityManager();
	}

	@After
	public void tearDown(){
		em.close();
	}
	
	
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
		
		 assertNotNull("Expected non null book", book);

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
