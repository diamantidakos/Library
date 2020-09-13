package com.mgiandia.library.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.util.SystemDate;
import com.mgiandia.library.util.SystemDateStub;

public class LoanServiceTest extends LibraryServiceTest {

	@Override
	protected void afterTearDown() {
		SystemDateStub.reset();
	}
	
	@Test
	public void noBorrowerJpa() {

		LoanService loanService = new LoanService(em);
		assertThrows(LibraryException.class, () -> {
			loanService.findBorrower(99999);
			loanService.borrow(Initializer.UML_DISTILLED_ID1);
		});
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBorrowJpa() {

		LoanService loanService = new LoanService(em);
		loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
		Assertions.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));

		em.clear();
		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();

		Loan loan = loanList.get(0);

		Assertions.assertTrue(loan.isPending());
		Assertions.assertEquals(Initializer.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
		Assertions.assertEquals(ItemState.LOANED, loan.getItem().getState());

	}

	@Test
	public void borrowDataBaseJpa() {

		LoanService loanService = new LoanService(em);
		loanService.findBorrower(Initializer.DIAMANTIDIS_ID);

		Assertions.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID1));
		Assertions.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
		Assertions.assertNotNull(loanService.borrow(Initializer.UML_REFACTORING_ID));
		Assertions.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID2));
		Assertions.assertNull(loanService.borrow(Initializer.UML_DISTILLED_ID2));
	}

	@Test
	public void testFindPendingLoans() {

		LoanService service = new LoanService(em);

		// some loans
		service.findBorrower(Initializer.GIAKOUMAKIS_ID);
		Assertions.assertNotNull(service.borrow(Initializer.UML_USER_GUIDE_ID1));
		Assertions.assertNotNull(service.borrow(Initializer.UML_DISTILLED_ID1));

		service.findBorrower(Initializer.DIAMANTIDIS_ID);
		Assertions.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
		// end of some loans

		List<Loan> loans = service.findPendingLoans(false);

		Assertions.assertEquals(loans.size(), 3);
		Assertions.assertNotNull(loans.get(0).getBorrower());
		Assertions.assertNotNull(loans.get(0).getItem());

		loans = service.findPendingLoans(true);
		Assertions.assertEquals(loans.size(), 0);

	}

	@Test
	public void testFindPendingLoanForNotReturnedItem() {

		
			LoanService service = new LoanService(em);

			service.findBorrower(Initializer.DIAMANTIDIS_ID);
			Assertions.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
			// end of some loans

			Loan foundLoan = service.findPendingLoan(Initializer.UML_REFACTORING_ID);

			Assertions.assertNotNull(foundLoan);

		
	}

	@Test
	public void testFindPendingLoanForAvailableItem() {

		
			LoanService service = new LoanService(em);

			Loan foundLoan = service.findPendingLoan(Initializer.UML_REFACTORING_ID);

			Assertions.assertNull(foundLoan);

		
	}

	@Test
	public void testFindPendingLoans_onlyOverdue() {

		
			LoanService service = new LoanService(em);

			// some loans
			service.findBorrower(Initializer.GIAKOUMAKIS_ID);
			Assertions.assertNotNull(service.borrow(Initializer.UML_USER_GUIDE_ID1));
			Assertions.assertNotNull(service.borrow(Initializer.UML_DISTILLED_ID1));

			service.findBorrower(Initializer.DIAMANTIDIS_ID);
			Assertions.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
			// end of some loans

			SystemDateStub.setStub(SystemDate.now().plusDays(200));

			List<Loan> loans = service.findPendingLoans(true);

			Assertions.assertEquals(loans.size(), 3);
			Assertions.assertNotNull(loans.get(0).getBorrower());
			Assertions.assertNotNull(loans.get(0).getItem());
		
	}


}
