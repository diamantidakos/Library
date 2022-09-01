package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.LOANS;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.LoanRepository;
import com.mgiandia.library.representation.LoanMapper;
import com.mgiandia.library.representation.LoanRepresentation;


@Path(LOANS)
public class LoanResource  {
	@Inject
	LoanRepository loanRepository;
	
	@Inject
	LoanMapper loanMapper;
	
	@GET
	@Path("/{loanId}")
	@Transactional
	public  LoanRepresentation find(@PathParam("loanId") Integer loanId) {
		Loan loan = loanRepository.findById(loanId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		return loanMapper.toRepresentation(loan);
	}
	
	@GET
	@Path("/item/{itemId}")
	@Transactional
	public  LoanRepresentation findForItem(@PathParam("itemId") Integer itemId) {
		Loan loan = loanRepository.activeForItem(itemId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		return loanMapper.toRepresentation(loan);
	}

	
	
	
	@POST
	@Path("/{loanId}/returnItem")
	@Transactional
	public Response returnItem(@PathParam("loanId") Integer loanId){
		Loan loan = loanRepository.findById(loanId);
		if (loan == null) {
			throw new NotFoundException();
		}
		
		loan.returnItem();
		
		return Response.noContent().build();
	}
	
	
	
}
