package com.mgiandia.library.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.persistence.JPAUtil;

public abstract class LibraryServiceTest {

	Initializer dataHelper;
	protected EntityManager em;

	public LibraryServiceTest() {
		super();
	}

	protected void beforeDatabasePreparation(){}
	
	protected void afterDatabasePreparation(){}

	@Before
	public final void setUp() {
		beforeDatabasePreparation();
		dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
		afterDatabasePreparation();
	}

	protected void afterTearDown(){}
	
	@After
	public final void tearDown() {
		em.close();
		afterTearDown();
	}

}