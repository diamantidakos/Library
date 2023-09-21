package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BORROWERS;

import java.net.URI;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

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
	@Path("/{borrowerNo}")
	@Transactional
	public BorrowerRepresentation find(@PathParam("borrowerNo") Integer borrowerNo) {
		Borrower b = borrowerRepository.findWithDetails(borrowerNo);
		
		if(b == null) {
			throw new NotFoundException();
		}
		return borrowerMapper.toRepresentation(b);
	}
	
	
	@PUT
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
	

	@PUT
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
