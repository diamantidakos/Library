package com.mgiandia.library.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

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
	
	@Test(expected = LibraryException.class)
	public void noBorrowerJpa() {

		LoanService loanService = new LoanService(em);
		loanService.findBorrower(99999);
		loanService.borrow(Initializer.UML_DISTILLED_ID1);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBorrowJpa() {

		LoanService loanService = new LoanService(em);
		loanService.findBorrower(Initializer.DIAMANTIDIS_ID);
		Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));

		em.clear();
		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();

		Loan loan = loanList.get(0);

		Assert.assertTrue(loan.isPending());
		Assert.assertEquals(Initializer.UML_DISTILLED_ID1, loan.getItem().getItemNumber());
		Assert.assertEquals(ItemState.LOANED, loan.getItem().getState());

	}

	@Test
	public void borrowDataBaseJpa() {

		LoanService loanService = new LoanService(em);
		loanService.findBorrower(Initializer.DIAMANTIDIS_ID);

		Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID1));
		Assert.assertNotNull(loanService.borrow(Initializer.UML_DISTILLED_ID1));
		Assert.assertNotNull(loanService.borrow(Initializer.UML_REFACTORING_ID));
		Assert.assertNotNull(loanService.borrow(Initializer.UML_USER_GUIDE_ID2));
		Assert.assertNull(loanService.borrow(Initializer.UML_DISTILLED_ID2));
	}

	@Test
	public void testFindPendingLoans() {

		LoanService service = new LoanService(em);

		// some loans
		service.findBorrower(Initializer.GIAKOUMAKIS_ID);
		Assert.assertNotNull(service.borrow(Initializer.UML_USER_GUIDE_ID1));
		Assert.assertNotNull(service.borrow(Initializer.UML_DISTILLED_ID1));

		service.findBorrower(Initializer.DIAMANTIDIS_ID);
		Assert.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
		// end of some loans

		List<Loan> loans = service.findPendingLoans(false);

		Assert.assertEquals(loans.size(), 3);
		Assert.assertNotNull(loans.get(0).getBorrower());
		Assert.assertNotNull(loans.get(0).getItem());

		loans = service.findPendingLoans(true);
		Assert.assertEquals(loans.size(), 0);

	}

	@Test
	public void testFindPendingLoanForNotReturnedItem() {

		
			LoanService service = new LoanService(em);

			service.findBorrower(Initializer.DIAMANTIDIS_ID);
			Assert.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
			// end of some loans

			Loan foundLoan = service.findPendingLoan(Initializer.UML_REFACTORING_ID);

			Assert.assertNotNull(foundLoan);

		
	}

	@Test
	public void testFindPendingLoanForAvailableItem() {

		
			LoanService service = new LoanService(em);

			Loan foundLoan = service.findPendingLoan(Initializer.UML_REFACTORING_ID);

			Assert.assertNull(foundLoan);

		
	}

	@Test
	public void testFindPendingLoans_onlyOverdue() {

		
			LoanService service = new LoanService(em);

			// some loans
			service.findBorrower(Initializer.GIAKOUMAKIS_ID);
			Assert.assertNotNull(service.borrow(Initializer.UML_USER_GUIDE_ID1));
			Assert.assertNotNull(service.borrow(Initializer.UML_DISTILLED_ID1));

			service.findBorrower(Initializer.DIAMANTIDIS_ID);
			Assert.assertNotNull(service.borrow(Initializer.UML_REFACTORING_ID));
			// end of some loans

			SystemDateStub.setStub(SystemDate.now().plusDays(200));

			List<Loan> loans = service.findPendingLoans(true);

			Assert.assertEquals(loans.size(), 3);
			Assert.assertNotNull(loans.get(0).getBorrower());
			Assert.assertNotNull(loans.get(0).getItem());
		
	}


}
