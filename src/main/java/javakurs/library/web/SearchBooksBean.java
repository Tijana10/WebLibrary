package javakurs.library.web;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javakurs.libary.domain.Category;
import javakurs.library.business.BookService;
import javakurs.library.dto.BookDTO;

@Named
@RequestScoped
public class SearchBooksBean {
	
	@Inject
	private BookService bookService;
	
	private String author;
	
	private String title;
	
	private String isbn;
	
	private Category category;
	
	private List<BookDTO> results = new ArrayList<>();
	
	
	
	

	
	public List<BookDTO> getResults() {
		return results;
	}





	public String getAuthor() {
		return author;
	}





	public void setAuthor(String author) {
		this.author = author;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getIsbn() {
		return isbn;
	}





	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}





	public Category getCategory() {
		return category;
	}





	public void setCategory(Category category) {
		this.category = category;
	}





	public String searchBooks() {
		results =	bookService.find(title, author, isbn, null);
		return "";
	}

}
