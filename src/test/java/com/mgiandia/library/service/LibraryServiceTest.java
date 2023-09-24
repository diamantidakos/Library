package com.mgiandia.library.service;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

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

	@BeforeEach
	public final void setUp() {
		beforeDatabasePreparation();
		dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
		afterDatabasePreparation();
	}

	protected void afterTearDown(){}
	
	@AfterEach
	public final void tearDown() {
		em.close();
		afterTearDown();
	}

}