package com.mgiandia.library.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Publisher;

public class CatalogService {

	private EntityManager em;

	public CatalogService(EntityManager em) {
		this.em = em;
	}

	public Book save(Book book) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (book.getId() != null) {
			// beware, always use the result of merge
			return em.merge(book);
		} else {
			em.persist(book);
		}
		tx.commit();
		return book;

	}

	@SuppressWarnings("unchecked")
	public List<Publisher> findAllPublishers() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Publisher> results = null;

		results = em.createQuery("select p from Publisher p").getResultList();

		tx.commit();
		return results;
	}

	public Book findBookById(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Book book = null;
		try {
			book = em.find(Book.class, id);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		return book;
	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthor(String authorName) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Book> results = null;
		results = em.createQuery("select books from Author a where a.person.lastName like :surname ")
				// "select b from Book b join fetch b.authors as a where
				// a.person.lastName like :surname ")
				.setParameter("surname", authorName).getResultList();

		tx.commit();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitle(String title) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Book> results = null;

		results = em.createQuery("select b from Book b where b.title like :title")
				// "select b from Book b left join fetch b.authors where b.title
				// like :title")
				.setParameter("title", "%" + title + "%").getResultList();
		tx.commit();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Book> results = null;

		results = em.createQuery("select b from Book b").getResultList();
		tx.commit();
		return results;
	}

	public boolean deleteBook(Book b) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (b != null) {
			em.remove(b);
			return true;
		}
		tx.commit();
		return false;

	}

}
