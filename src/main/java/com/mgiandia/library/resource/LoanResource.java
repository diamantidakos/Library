package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.LOANS;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.service.LoanService;
import com.mgiandia.library.service.ReturnService;
import com.mgiandia.library.util.HttpError;


@Path(LOANS)
public class LoanResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLoan(LoanInfo loanInfo) {

		EntityManager em = getEntityManager();

		LoanService loanService = new LoanService(em);

		Response response = null;

		try {
			loanService.loanItem(loanInfo.getItemId(), loanInfo.getBorrowerNo());

			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newLoanUri = ub.path(LibraryUri.loanUri(Integer.toString(loanInfo.getItemId()))).build();
			// newLoanUri -> /loans/{itemId}
			response = Response.created(newLoanUri).build();

		} catch (LibraryException e) {
			HttpError error = HttpError.httpForbiddenError(e.getMessage());
			response = Response.status(Status.FORBIDDEN).entity(Entity.entity(error, MediaType.APPLICATION_JSON))
					.build();
		}

		em.close();

		return response;
	}

	@DELETE
	@Path("{itemId:[0-9]*}")
	public Response returnItem(@PathParam("itemId")int itemId){
		
		EntityManager em = getEntityManager();
		
		ReturnService service = new ReturnService(em);
		
		Response response= null;
		try {
			service.returnItem(itemId);
			response = Response.ok().build();
		} catch (LibraryException e) {
			
			HttpError error = HttpError.httpNotFoundError(e.getMessage());
			response = Response.status(Status.NOT_FOUND).entity(Entity.entity(error, MediaType.APPLICATION_JSON))
					.build();
			
		}
		
		em.close();
		
		return response;
		
	}
	
	
	
}
