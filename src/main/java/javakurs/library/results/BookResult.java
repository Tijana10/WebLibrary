package javakurs.library.results;

import javax.xml.bind.annotation.XmlRootElement;

import javakurs.libarary.errorhandling.LibraryException;
import javakurs.libarary.errorhandling.Pair;
import javakurs.library.dto.BookDTO;

@XmlRootElement
public class BookResult extends Result {
	
	private BookDTO book;
	
	public BookResult() {
		
	}
	
	public BookResult(int errorCode, String errorMessage, BookDTO book) {
		super(errorCode, errorMessage);
		this.book = book;
	}
	
	public BookResult(Pair<Integer, String> pair, BookDTO book) {
		this(pair.getFirst(), pair.getSecond(), book);
	}
	
	public BookResult(Pair<Integer, String> pair) {
		
		this(pair, null);
	}
	
	public BookResult(BookDTO book) {
		this(LibraryException.OK, book);
	}
	
	public BookResult(LibraryException ex) {
		
		this(ex.getError(), null);
	}
	
	

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}
	
	
	

}
