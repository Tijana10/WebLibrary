package javakurs.library.results;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import javakurs.libarary.errorhandling.Pair;
import javakurs.library.dto.BookDTO;

@XmlRootElement
public class BooksResult extends Result{
	
	private List<BookDTO> books;
	
	public BooksResult() {
		
	}
	
	public BooksResult(int errorCode, String errorMessage, List<BookDTO> books) {
		super(errorCode, errorMessage);
		this.books = books;
	}
	
	public BooksResult(Pair<Integer, String> pair, List<BookDTO> books) {
		this(pair.getFirst(), pair.getSecond(), books);
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	
	

}
