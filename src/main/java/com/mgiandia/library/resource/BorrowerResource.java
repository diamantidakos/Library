package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BORROWERS;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.BorrowerRepository;
import com.mgiandia.library.representation.BorrowerMapper;
import com.mgiandia.library.representation.BorrowerRepresentation;

@Path(BORROWERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BorrowerResource {

	@Inject
	BorrowerRepository borrowerRepository;
	
	@Inject
	BorrowerMapper borrowerMapper;
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Transactional
	public List<BorrowerRepresentation> listAll() {
		return borrowerMapper.toRepresentationList(borrowerRepository.listAll());
	}
	
	@GET
	@Path("{borrowerNo}")
	@Transactional
	public BorrowerRepresentation find(@PathParam("borrowerNo") Integer borrowerNo) {
		Borrower b = borrowerRepository.findWithDetails(borrowerNo);
		if(b == null) {
			throw new NotFoundException();
		}
		return borrowerMapper.toRepresentation(b);
	}
	
	
	@POST
	@Transactional
	public Response create (BorrowerRepresentation representation) {
		if (representation.borrowerNo == null) {
			throw new RuntimeException();			
		}

		Borrower borrower = borrowerMapper.toModel(representation);
		borrowerRepository.persist(borrower);
		URI uri = UriBuilder.fromResource(BorrowerResource.class).path(String.valueOf(borrower.getBorrowerNo())).build();
		return Response.created(uri).entity(borrowerMapper.toRepresentation(borrower)).build();
	}
	

	@POST
	@Path("/{id}")
	@Transactional
	public Response update(@PathParam("id") Integer id,
			BorrowerRepresentation representation) {
		if (! id.equals(representation.borrowerNo)) {
			throw new RuntimeException();
		}
		
		Borrower borrower = borrowerMapper.toModel(representation);
		borrowerRepository.getEntityManager().merge(borrower);
		
		return Response.noContent().build();
	}
	
	
}
