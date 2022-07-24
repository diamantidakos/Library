package com.mgiandia.library.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Publisher;
@RequestScoped
public class CatalogService {

	@Inject
	EntityManager em;

	
    @Transactional
	public Book save(Book book) {

		if (book.getId() != null) {
			// beware, always use the result of merge
			book = em.merge(book);
		} else {
			em.persist(book);
		}
		return book;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Publisher> findAllPublishers() {
		List<Publisher> results = null;
		results = em.createQuery("select p from Publisher p").getResultList();
		return results;
	}

	
	@Transactional
	public Book findBookById(int id) {
		Book book = null;
        book = em.find(Book.class, id);
		return book;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> findBooksByAuthor(String authorName) {

		List<Book> results = null;
		results = em.createQuery("select books from Author a where a.person.lastName like :surname ")
				// "select b from Book b join fetch b.authors as a where
				// a.person.lastName like :surname ")
				.setParameter("surname", authorName).getResultList();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> findBooksByTitle(String title) {
		List<Book> results = null;

		results = em.createQuery("select b from Book b join fetch b.publisher p where b.title like :title")
				// "select b from Book b left join fetch b.authors where b.title
				// like :title")
				.setParameter("title", "%" + title + "%").getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> findAllBooks() {
		List<Book> results = null;
		results = em.createQuery("select b from Book b").getResultList();
		return results;
	}

	@Transactional
	public boolean deleteBook(Book b) {
		if (b != null) {
			em.remove(b);
			return true;
		}
		return false;

	}
	
	@Transactional
	public boolean deleteBook(int bookId) {
		Book book = em.getReference(Book.class, bookId);
		em.remove(book);
		return true;

	}

}
