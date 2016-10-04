package com.mgiandia.library.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;

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

}