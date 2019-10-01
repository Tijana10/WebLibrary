package javakurs.library.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javakurs.libarary.errorhandling.LibraryException;
import javakurs.libary.domain.Book;
import javakurs.libary.domain.Category;
import javakurs.library.dao.BookDAO;
import javakurs.library.dto.BookDTO;

@Stateless
public class BookService {
	
	private static Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@EJB
	private BookDAO bookDAO;
	
	public int createBook(Book book) {
		
		validateInput(book);
		
		Book b = bookDAO.find(book.getTitle());
		
		if(b != null) {
			
			throw new LibraryException(LibraryException.BOOK_ALREADY_EXISTS);
			
		}
		
		try {
		
			bookDAO.save(book);
		
		} catch(PersistenceException pe) {
			
			throw new LibraryException(LibraryException.DATABASE_ERROR);
		
		}
		
		return book.getId();
		
	}
	
	private void validateInput(Book book) {
		
		if(book.getTitle() == null || book.getTitle().length() > 150 || book.getTitle().length() ==0) {
			
			throw new LibraryException(LibraryException.TITLE_INVALID_LENGTH);
			
		}
		
		if(book.getAuthor() == null || book.getAuthor().length() > 50 ||  book.getAuthor().length() == 0) {
			
			throw new LibraryException(LibraryException.AUTHOR_INVALID_LENGTH);
		}
		
		
		logger.info("ISBN length: {}", book.getIsbn().length());
		
		if(book.getIsbn() == null || book.getIsbn().length() != 13) {
			
			throw new LibraryException(LibraryException.INVALID_ISBN);
			
		}
		
		if(book.getDescription() == null || book.getDescription().length() > 2500) {
			
			throw new LibraryException(LibraryException.DESCRIPTION_INVALID_LENGTH);
			
		}
		
		if(book.getCategory() == null) {
			
			throw new LibraryException(LibraryException.CATEGORY_NOT_PRESENT);
			
		}
		
		
		
	}
	
	public List<BookDTO> getBooks(){
		
		List<Book> books = bookDAO.listBooks();
		
		List<BookDTO> dtoBooks = new ArrayList<>();
		
		for(Book b : books) {
			dtoBooks.add(b.asMinimalDTO());
		}
		
		return dtoBooks;
	}
	
	public List<BookDTO> getFullBooks(){
		List<Book> _books = bookDAO.listBooks();
		
		List<BookDTO> books = new ArrayList<>();
		
		for(Book b : _books) {
			books.add(b.asBookDTO());
		}
		
		return books;
	}
	
	public Book getBook(int bookID) {
		
		Book book = bookDAO.find(bookID);
		
		if(book == null) {
			
			throw new LibraryException(LibraryException.BOOK_ID_DONT_EXIST);
			
		}
		
		return book;
	}
	
	public Book editBook(int bookID, BookDTO bookDTO) {
		
		validateInput(bookDTO.asBook());
		
		Book aBook = bookDAO.find(bookID);
		
		if(aBook == null) {
			
			throw new LibraryException(LibraryException.BOOK_ID_DONT_EXIST);
			
		}
		
		Book titleBook = bookDAO.find(bookDTO.getTitle());
		
		if(titleBook != null && aBook.getId() != titleBook.getId() ){
			
			throw new LibraryException(LibraryException.BOOK_ALREADY_EXISTS);
		}
		
		aBook.update(bookDTO);
		
		return aBook;
		
	}
	
	public void removeBook(int bookID) {
		
		Book book = bookDAO.find(bookID);
		
		if(book == null) {
			
			throw new LibraryException(LibraryException.BOOK_ID_DONT_EXIST);
			
		}
		
		bookDAO.remove(book);
	}
	
	public List<BookDTO> find(String title, String author, String isbn, Category category){
		
		if(isbn != null && !isbn.isEmpty() && isbn.length() != 13) {
			
			throw new LibraryException(LibraryException.INVALID_ISBN);
			
		}
		
		List<Book> books = bookDAO.find(title, author, isbn, category);
		
		List<BookDTO> dtos = new ArrayList<>();
		
		for(Book b : books) {
			dtos.add(b.asMinimalDTO());
		}
		
		return dtos;
	}

}
