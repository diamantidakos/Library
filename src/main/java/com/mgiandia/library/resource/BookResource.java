package com.mgiandia.library.resource;

import static com.mgiandia.library.resource.LibraryUri.BOOKS;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.service.CatalogService;

@Path(BOOKS)
public class BookResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookInfo> listAllBooks() {
		EntityManager em = getEntityManager();
		CatalogService catalogService = new CatalogService(em);
		List<Book> books = catalogService.findAllBooks();

		List<BookInfo> bookInfo = BookInfo.wrap(books);

		em.close();
		return bookInfo;

	}

	@GET
	@Path("{bookId:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public BookInfo getBookDetails(@PathParam("bookId") int bookId) {

		EntityManager em = getEntityManager();

		CatalogService catalogService = new CatalogService(em);
		Book book = catalogService.findBookById(bookId);

		BookInfo bookInfo = BookInfo.wrap(book);
		em.close();

		return bookInfo;

	}

	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookInfo> searchBookByTitle(@QueryParam("title") String title) {

		EntityManager em = getEntityManager();
		CatalogService catalogService = new CatalogService(em);
		List<Book> books = catalogService.findBooksByTitle(title);

		List<BookInfo> booksInfo = BookInfo.wrap(books);

		em.close();
		return booksInfo;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(BookInfo bookInfo) {

		EntityManager em = getEntityManager();

		Book book = bookInfo.getBook(em);
		// TODO: should validate book

		CatalogService catalogService = new CatalogService(em);
		book = catalogService.save(book);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newBookUri = ub.path(Integer.toString(book.getId())).build();

		em.close();

		return Response.created(newBookUri).build();
	}

	/**
	 * Update a specific book
	 * 
	 * @param bookInfo
	 *            A full representation of the book, including its id should be
	 *            submitted
	 * @return
	 */
	@PUT
	@Path("{bookId:[0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook(BookInfo bookInfo) {

		EntityManager em = getEntityManager();

		Book book = bookInfo.getBook(em);
		// TODO: should validate book

		CatalogService catalogService = new CatalogService(em);
		book = catalogService.save(book);

		em.close();

		return Response.ok().build();
	}

	@DELETE
	@Path("{bookId:[0-9]*}")
	public Response deleteBook(@PathParam("bookId") int bookId) {

		EntityManager em = getEntityManager();
		
		CatalogService service = new CatalogService(em);
		boolean result = service.deleteBook(bookId);
		
		if (!result) {
			em.close();
			return Response.status(Status.NOT_FOUND).build();
		}

		em.close();
		return Response.ok().build();

	}

}
