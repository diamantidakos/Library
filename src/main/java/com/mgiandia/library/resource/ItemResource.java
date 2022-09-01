package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.ITEMS;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.BorrowerRepository;
import com.mgiandia.library.persistence.ItemRepository;
import com.mgiandia.library.persistence.LoanRepository;
import com.mgiandia.library.representation.ItemMapper;
import com.mgiandia.library.representation.ItemRepresentation;
import com.mgiandia.library.representation.LoanMapper;

@Path(ITEMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ItemResource {

	@Inject
	ItemRepository itemRepository;
	
	@Inject
	ItemMapper itemMapper;
	
	@Inject
	BorrowerRepository borrowerRepository;
	
	@Inject
	LoanRepository loanRepository;
	
	@Inject
	LoanMapper loanMapper;
	
	
	@GET
	@Path("/{itemNo}")
	@Transactional
	public  ItemRepresentation find(@PathParam("itemNo") Integer itemNo) {
		return itemMapper.toRepresentation(itemRepository.findById(itemNo));
	}

	@GET
	@Transactional
	public  List<ItemRepresentation> list() {
		return itemMapper.toRepresentationList(itemRepository.listAll());
	}
	
	@POST
	@Path("/{itemNo}/loan/{borrowerNo}")
	@Consumes(MediaType.MEDIA_TYPE_WILDCARD)
	@Transactional
	public Response loan(@PathParam("itemNo") Integer itemNo,
			@PathParam("borrowerNo") Integer borrowerNo) {
		
		Item item = itemRepository.findById(itemNo);
		
		if (item == null) {
			throw new RuntimeException();
		}
		
		Borrower borrower = borrowerRepository.findById(borrowerNo);
		Loan loan = item.borrow(borrower);
		
		loanRepository.persist(loan);
		
		URI uri = UriBuilder.fromResource(LoanResource.class).path(String.valueOf(loan.getId())).build();
		
		return Response.created(uri).entity(loanMapper.toRepresentation(loan)).build();
	}
	
	
}
