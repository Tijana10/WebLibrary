package javakurs.libary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javakurs.library.dto.BookDTO;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
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
	
	public Book() {
		
	}
	
	public Book(BookDTO bookDTO) {
		
		this.title = bookDTO.getTitle();
		this.author = bookDTO.getAuthor();
		this.description = bookDTO.getDescription();
		this.category = bookDTO.getCategory();
		this.isbn = bookDTO.getIsbn();
	}
	
	public BookDTO asMinimalDTO() {
		
		BookDTO book = new BookDTO();
		
		book.setId(this.id);
		book.setTitle(this.title);
		
		return book;
	}
	
	public BookDTO asBookDTO() {
		
		return new BookDTO(this);
		
	}
	
	public void update(BookDTO bookDTO) {
		
		this.title = bookDTO.getTitle();
		this.author = bookDTO.getAuthor();
		this.isbn = bookDTO.getIsbn();
		this.description = bookDTO.getDescription();
		this.category = bookDTO.getCategory();
		
	}
	
	

}
