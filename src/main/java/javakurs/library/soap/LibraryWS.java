package javakurs.library.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import javakurs.libarary.errorhandling.LibraryException;
import javakurs.libary.domain.Book;
import javakurs.libary.domain.Category;
import javakurs.library.business.BookService;
import javakurs.library.dto.BookDTO;
import javakurs.library.results.BookCreateResult;
import javakurs.library.results.BookResult;
import javakurs.library.results.BooksResult;
import javakurs.library.results.Result;

@Stateless
@WebService
public class LibraryWS {
	
	@EJB
	private BookService bookService;
	
	public BookCreateResult createBook(BookDTO bookDTO) {
		
		Book book = bookDTO.asBook();
		
		try {
			
			int id = bookService.createBook(book);
			
			return new BookCreateResult(LibraryException.OK, id);
			
		} catch(LibraryException ex) {
			
			return new BookCreateResult(ex.getError());
			
		}
		
	}
	
	public Result editBook(int bookID, BookDTO bookDTO) {
		
		try {
			
			Book book = bookService.editBook(bookID, bookDTO);
			
			return new Result(LibraryException.OK);
			
		} catch(LibraryException ex) {
			
			return new Result(ex.getError());
		}
		
	}
	
	public BookResult getBook(int bookID) {
		
		try {
			
			Book book = bookService.getBook(bookID);
			BookDTO bookDTO = book.asBookDTO();
			
			return new BookResult(LibraryException.OK, bookDTO);
			
			
		} catch(LibraryException ex) {
			
			return new BookResult(ex.getError(), null);
			
		}
		
		
	}
	
	public BooksResult getBooks() {
		
		try {
			
			
			return new BooksResult(LibraryException.OK, bookService.getBooks());
			
		} catch(LibraryException ex) {
			
			return new BooksResult(ex.getError(), null);
			
		}
		
	}
	
	public Result deleteBook(int bookID) {
		
		try {
			
			bookService.removeBook(bookID);
			
			return new Result(LibraryException.OK);
			
		} catch (LibraryException ex) {
			
			return new Result(ex.getError());
		}
	}
	
	public BooksResult searchBooks(String title, String author, String isbn, Category category) {
		
		BookDTO book = new BookDTO();
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setCategory(category);
		
		return find(book);
	}
	
	
	public BooksResult find(BookDTO book) {
		
		try {
			
			List<BookDTO> books = bookService.find(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getCategory());
			
			return new BooksResult(LibraryException.OK, books);
		
		} catch(LibraryException ex) {
			
			return new BooksResult(ex.getError(), null);
			
		}
	}

}
