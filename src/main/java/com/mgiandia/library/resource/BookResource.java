package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BOOKS;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.persistence.BookRepository;
import com.mgiandia.library.representation.BookMapper;
import com.mgiandia.library.representation.BookRepresentation;

@Path(BOOKS)
@RequestScoped
public class BookResource {

	@Context
	UriInfo uriInfo;
	
	@Inject 
	BookRepository bookRepository;
	
	@Inject
	BookMapper bookMapper;
	
	@Inject
	EntityManager em;

	@GET
	@Path("{bookId:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("bookId") Integer bookId) {

		Book book = bookRepository.findById(bookId);
		if (book == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(bookMapper.toRepresentation(book)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookRepresentation> searchBookByTitle(@QueryParam("title") String title) {
		return bookMapper.toRepresentationList(bookRepository.search(title));
	}



}
