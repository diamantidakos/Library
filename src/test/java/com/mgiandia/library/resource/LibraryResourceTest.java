package com.mgiandia.library.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;
import com.mgiandia.library.service.BorrowerService;
import com.mgiandia.library.service.CatalogService;
import com.mgiandia.library.service.LoanService;

public abstract class LibraryResourceTest extends JerseyTest {

	Initializer dataHelper;

	public LibraryResourceTest() {
		super();
	}

	public LibraryResourceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}

	public LibraryResourceTest(Application jaxrsApplication) {
		super(jaxrsApplication);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}

	public List<Publisher> listPublishers() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		List<Publisher> publishers = em.createQuery("select p from Publisher p").getResultList();
		
		tx.commit();
		em.close();
		return publishers;
	}
	
	public List<Book> findBooksByTitle(String title) {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		CatalogService service = new CatalogService(em);
		List<Book> books = service.findBooksByTitle(title);
		
		em.close();
		return books;
	}

	protected List<Borrower> findBorrowerByLastName(String lastName) {
		EntityManager em = JPAUtil.getCurrentEntityManager();
	
		BorrowerService service = new BorrowerService(em);
	
		List<Borrower> publishers = service.findBorrowersByLastName(lastName);
	
		em.close();
		return publishers;
	}

	protected Loan findPendingLoanForItem(int itemId) {
	
		EntityManager em = JPAUtil.getCurrentEntityManager();
		LoanService service = new LoanService(em);
	
		Loan loan = service.findPendingLoan(itemId);
		em.close();
		return loan;
	}
	
	
	

}