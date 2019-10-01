package javakurs.library.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javakurs.libarary.errorhandling.LibraryException;
import javakurs.libary.domain.Book;
import javakurs.library.dto.BookDTO;
import javakurs.library.results.BookCreateResult;
import javakurs.library.results.BookResult;
import javakurs.library.results.BooksResult;
import javakurs.library.results.Result;
import javakurs.library.soap.LibraryWS;

@Path("book2")
public class Book2Resource {
	
	@EJB
	private LibraryWS libraryWS;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BooksResult getBooks() {
		
		return libraryWS.getBooks();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public BookResult getBook(@PathParam("id") int bookId) {
		
		return libraryWS.getBook(bookId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BookCreateResult createBook(BookDTO book) {
		
		return libraryWS.createBook(book);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Result editBook(BookDTO book, @PathParam("id") int bookId) {
		
		return libraryWS.editBook(bookId, book);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Result deleteBook(@PathParam("id") int bookId){
		
		return libraryWS.deleteBook(bookId);
		
	}


}
