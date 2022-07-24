package com.mgiandia.library.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.SystemDate;
import com.mgiandia.library.util.SystemDateStub;
@QuarkusTest
public class LoanServiceTest {

	@Inject
	LoanService loanService;
	
	@Inject
	EntityManager em;
	
	@AfterEach
	protected void afterTearDown() {
		SystemDateStub.reset();
	}
	
	@Test
	@TestTransaction 
	public void noBorrowerJpa() {
		assertThrows(LibraryException.class, () -> {
			loanService.findBorrower(99999);
			loanService.borrow(Fixture.UML_DISTILLED_ID1);
		});
		
	}

	@SuppressWarnings("unchecked")
	@Test
	@TestTransaction 
	public void testBorrowJpa() {

		loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_DISTILLED_ID1));

		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();

		Loan loan = loanList.get(0);

		Assertions.assertTrue(loan.isPending());
		Assertions.assertEquals(Fixture.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
		Assertions.assertEquals(ItemState.LOANED, loan.getItem().getState());

	}

	@Test
	@TestTransaction 
	public void borrowDataBaseJpa() {

		loanService.findBorrower(Fixture.DIAMANTIDIS_ID);

		Assertions.assertNotNull(loanService.borrow(Fixture.UML_USER_GUIDE_ID1));
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_DISTILLED_ID1));
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_REFACTORING_ID));
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_USER_GUIDE_ID2));
		Assertions.assertNull(loanService.borrow(Fixture.UML_DISTILLED_ID2));
	}

	@Test
	@TestTransaction 
	public void testFindPendingLoans() {


		// some loans
		loanService.findBorrower(Fixture.GIAKOUMAKIS_ID);
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_USER_GUIDE_ID1));
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_DISTILLED_ID1));

		loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
		Assertions.assertNotNull(loanService.borrow(Fixture.UML_REFACTORING_ID));
		// end of some loans

		List<Loan> loans = loanService.findPendingLoans(false);

		Assertions.assertEquals(loans.size(), 3);
		Assertions.assertNotNull(loans.get(0).getBorrower());
		Assertions.assertNotNull(loans.get(0).getItem());

		loans = loanService.findPendingLoans(true);
		Assertions.assertEquals(loans.size(), 0);

	}

	@Test
	@TestTransaction 
	public void testFindPendingLoanForNotReturnedItem() {


			loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
			Assertions.assertNotNull(loanService.borrow(Fixture.UML_REFACTORING_ID));
			// end of some loans

			Loan foundLoan = loanService.findPendingLoan(Fixture.UML_REFACTORING_ID);

			Assertions.assertNotNull(foundLoan);

		
	}

	@Test
	@TestTransaction 
	public void testFindPendingLoanForAvailableItem() {
		
			Loan foundLoan = loanService.findPendingLoan(Fixture.UML_REFACTORING_ID);

			Assertions.assertNull(foundLoan);

		
	}

	@Test
	@TestTransaction 
	public void testFindPendingLoans_onlyOverdue() {

		
			// some loans
		loanService.findBorrower(Fixture.GIAKOUMAKIS_ID);
			Assertions.assertNotNull(loanService.borrow(Fixture.UML_USER_GUIDE_ID1));
			Assertions.assertNotNull(loanService.borrow(Fixture.UML_DISTILLED_ID1));

			loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
			Assertions.assertNotNull(loanService.borrow(Fixture.UML_REFACTORING_ID));
			// end of some loans

			SystemDateStub.setStub(SystemDate.now().plusDays(200));

			List<Loan> loans = loanService.findPendingLoans(true);

			Assertions.assertEquals(loans.size(), 3);
			Assertions.assertNotNull(loans.get(0).getBorrower());
			Assertions.assertNotNull(loans.get(0).getItem());
		
	}


}
