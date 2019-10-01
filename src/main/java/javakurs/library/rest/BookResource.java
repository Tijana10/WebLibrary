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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javakurs.libarary.errorhandling.LibraryException;
import javakurs.libary.domain.Book;
import javakurs.libary.domain.Category;
import javakurs.library.business.BookService;
import javakurs.library.dto.BookDTO;
import javakurs.library.results.BookCreateResult;
import javakurs.library.results.BookResult;
import javakurs.library.results.BooksResult;
import javakurs.library.results.Result;

@Path("book")
public class BookResource {
	
	@EJB
	private BookService bookService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BooksResult getBooks() {
		
		try {
			
			List<BookDTO> books = bookService.getBooks();
			return new BooksResult(LibraryException.OK, books);
			
		} catch (LibraryException ex) {
			
			return new BooksResult(ex.getError(), null);
			
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public BookResult getBook(@PathParam("id") int bookId) {
		
		try {
			
			Book book = bookService.getBook(bookId);
			BookDTO aBook = book.asBookDTO();
			
			return new BookResult(aBook);
			
		} catch( LibraryException ex) {
			
		
			return new BookResult(ex);
			
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BookCreateResult createBook(BookDTO book) {
		
		try {
			
			int bookId = bookService.createBook(book.asBook());
			
			return new BookCreateResult(LibraryException.OK, bookId);
			
		} catch(LibraryException ex) {
			
			return new BookCreateResult(ex.getError(), 0);
			
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public BookResult editBook(BookDTO book, @PathParam("id") int bookId) {
		
		try {
			
			Book aBook = bookService.editBook(bookId, book);
			return new BookResult(aBook.asBookDTO());
			
		} catch(LibraryException ex) {
			
			return new BookResult(ex);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Result deleteBook(@PathParam("id") int bookId){
		
		try {
			bookService.removeBook(bookId);
			return new Result(LibraryException.OK);
			
		} catch(LibraryException ex) {
			return new Result(ex.getError());
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("q")
	public BooksResult searchBooks(@QueryParam("title") String title, @QueryParam("author")String author, @QueryParam("isbn")String isbn, @QueryParam("category")Category category) {
		
		try {
			List<BookDTO> books = bookService.find(title, author, isbn, category);
			return new BooksResult(LibraryException.OK, books);
			
		} catch(LibraryException ex) {
			return new BooksResult(ex.getError(), null);
			
		}
	}

}
