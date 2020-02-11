package com.mgiandia.library.resource;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.Initializer;

public class LoanResourceTest extends LibraryResourceTest {

	@Override
	protected Application configure() {
		/*
		 * 
		 */
		return new ResourceConfig(LoanResource.class, DebugExceptionMapper.class);
	}

	@Test
	public void testCreateNewLoan() {

		// find a borrower through search
		List<Borrower> foundBorrowers = findBorrowerByLastName("Διαμαντίδης");
		Assert.assertEquals(1, foundBorrowers.size());

		Borrower ndia = foundBorrowers.get(0);

		LoanRequestInfo loanInfo = new LoanRequestInfo(Initializer.UML_REFACTORING_ID, ndia.getBorrowerNo());

		Response response = target(LibraryUri.LOANS).request()
				.post(Entity.entity(loanInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		// assert the existence of the location element
		String location = response.getHeaderString(HttpHeaders.LOCATION);
		Assert.assertNotNull(location);

		// assert properties of new loan
		Loan newLoan = findPendingLoanForItem(Initializer.UML_REFACTORING_ID);
		Assert.assertNotNull(newLoan);
		Assert.assertEquals(Initializer.UML_REFACTORING_ID, newLoan.getItem().getItemNumber());
		Assert.assertEquals(ndia.getBorrowerNo(), newLoan.getBorrower().getBorrowerNo());

	}

	@Test
	public void testReturnLoanedItem() {

		// borrow an item
		List<Borrower> foundBorrowers = findBorrowerByLastName("Διαμαντίδης");
		Assert.assertEquals(1, foundBorrowers.size());

		Borrower ndia = foundBorrowers.get(0);

		LoanRequestInfo loanInfo = new LoanRequestInfo(Initializer.UML_REFACTORING_ID, ndia.getBorrowerNo());

		Response response = target(LibraryUri.LOANS).request()
				.post(Entity.entity(loanInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(response.getStatus(), Status.CREATED.getStatusCode());

		// now return it
		Response returnItemResponse = target(LibraryUri.loanUri(Integer.toString(Initializer.UML_REFACTORING_ID)))
				.request().delete();
		Assert.assertEquals(returnItemResponse.getStatus(), Status.OK.getStatusCode());

		// check database state
		Assert.assertNull(findPendingLoanForItem(Initializer.UML_REFACTORING_ID));
		
	}

}
