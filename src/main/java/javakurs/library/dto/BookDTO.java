package javakurs.library.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import javakurs.libary.domain.Book;
import javakurs.libary.domain.Category;

@XmlRootElement
public class BookDTO {
	
	private int id;
	
	private String title;
	
	private String author;
	
	private String isbn;
	
	private String description;
	
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Book asBook() {
		
		return new Book(this);
		
	}
	
	public static BookDTO asMinimalDTO(Book book) {
		
		BookDTO aBook = new BookDTO();
		
		aBook.setId(book.getId());
		aBook.setTitle(book.getTitle());
		
		return aBook;
	}
	
	public BookDTO() {
		
	}
	
	public BookDTO(Book book) {
		
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.description = book.getDescription();
		this.isbn = book.getIsbn();
		this.category = book.getCategory();
		
	}
	
	public List<Category> getCategories(){
		List<Category> cats = new ArrayList<>();
		for(Category c : Category.values()) {
			cats.add(c);
		}
		
		return cats;
	}
	
	
	

}
