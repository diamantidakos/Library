package com.mgiandia.library.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SystemDateStub;

public class ReturnServiceTest extends LibraryServiceTest {



	@Override
	protected void afterTearDown() {
		SystemDateStub.reset();
	}

	@Test(expected = LibraryException.class)
	public void returnWhenNoLoanExistJpa() {
		returnWhenNoLoanExist();
	}

	public void returnWhenNoLoanExist() {
		ReturnService service = new ReturnService(em);
		service.returnItem(2);
	}

	@Test
	public void confirmReturnedItemJpa() {
		confirmReturnedItem();
	}

	public void confirmReturnedItem() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo2ndMarch2007();
		ReturnService service = new ReturnService(em);
		service.returnItem(Initializer.UML_USER_GUIDE_ID1);

//		EntityManager em = JPAUtil.createEntityManager();
		
		em.clear();

		@SuppressWarnings("unchecked")
		List<Loan> loanList = em.createQuery("select l from Loan l").getResultList();
		Loan loan = loanList.get(0);
		Assert.assertEquals(LocalDate.of(2007, 3, 1), loan.getLoanDate());
		Assert.assertEquals(LocalDate.of(2007, 3, 2), loan.getReturnDate());
		Assert.assertEquals(Initializer.UML_USER_GUIDE_ID1, loan.getItem().getItemNumber());
		Assert.assertEquals(ItemState.AVAILABLE, loan.getItem().getState());
//		em.close();
	}

	@Test
	public void returnNoFineJpa() {
		returnNoFine();
	}

	public void returnNoFine() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo2ndMarch2007();
		ReturnService service = new ReturnService(em);
		Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
		Assert.assertNull(fine);
	}

	@Test
	public void returnWithFineJpa() {
		returnNoFine();
	}

	public void returnWithFine() {
		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();
		setSystemDateTo30thMarch2007();
		ReturnService service = new ReturnService(em);
		Money fine = service.returnItem(Initializer.UML_USER_GUIDE_ID1);
		Assert.assertNotNull(fine);
	}

	private void borrowUMLUserGuideToDiamantidis() {
		LoanService service = new LoanService(em);
		service.findBorrower(Initializer.DIAMANTIDIS_ID);
		service.borrow(Initializer.UML_USER_GUIDE_ID1);
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
