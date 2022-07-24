package com.mgiandia.library.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SystemDateStub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
@QuarkusTest
public class ReturnServiceTest {
	
	@Inject
	LoanService loanService;

	@Inject
	ReturnService returnService;
	
	@Inject
	EntityManager em;

	@AfterEach
	protected void afterTearDown() {
		SystemDateStub.reset();
	}

	@Test
	@TestTransaction 
	public void returnWhenNoLoanExistJpa() {
		Assertions.assertThrows(LibraryException.class, () -> {
			returnWhenNoLoanExist();	
		});
		
	}

	public void returnWhenNoLoanExist() {
		returnService.returnItem(2);
	}

	@Test
	@TestTransaction 
	public void confirmReturnedItemJpa() {
		confirmReturnedItem();
	}

	public void confirmReturnedItem() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo2ndMarch2007();
		returnService.returnItem(Fixture.UML_USER_GUIDE_ID1);


		@SuppressWarnings("unchecked")
		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();
		Loan loan = loanList.get(0);
		Assertions.assertEquals(LocalDate.of(2007, 3, 1), loan.getLoanDate());
		Assertions.assertEquals(LocalDate.of(2007, 3, 2), loan.getReturnDate());
		Assertions.assertEquals(Fixture.UML_USER_GUIDE_ID1, loan.getItem().getItemNumber());
		Assertions.assertEquals(ItemState.AVAILABLE, loan.getItem().getState());
	}

	@Test
	@TestTransaction 
	public void returnNoFineJpa() {
		returnNoFine();
	}

	public void returnNoFine() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo2ndMarch2007();
		Money fine = returnService.returnItem(Fixture.UML_USER_GUIDE_ID1);
		Assertions.assertNull(fine);
	}

	@Test
	@TestTransaction 
	public void returnWithFineJpa() {
		returnNoFine();
	}

	public void returnWithFine() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo30thMarch2007();
		Money fine = returnService.returnItem(Fixture.UML_USER_GUIDE_ID1);
		Assertions.assertNotNull(fine);
	}

	private void borrowUMLUserGuideToDiamantidis() {
		loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
		loanService.borrow(Fixture.UML_USER_GUIDE_ID1);
	}

	private void setSystemDateTo1stMarch2007() {
		 SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
	}

	private void setSystemDateTo2ndMarch2007() {
		SystemDateStub.setStub(LocalDate.of(2007, 3, 2));
	}

	private void setSystemDateTo30thMarch2007() {
		  SystemDateStub.setStub(LocalDate.of(2007, 3, 30));
	}


}
