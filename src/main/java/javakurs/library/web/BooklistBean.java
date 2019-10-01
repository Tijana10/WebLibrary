package javakurs.library.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javakurs.library.business.BookService;
import javakurs.library.dto.BookDTO;

@Named
@RequestScoped
public class BooklistBean {
	

	@EJB
	private BookService bookService;
	
	private List<BookDTO> books;
	
	private int booksPerPage = 5;
	
	private int page;
	
	private int pages;
	
	@PostConstruct
	public void init() {
		books = bookService.getFullBooks();
		page = 1;
		pages = books.size() / booksPerPage;
		
		if(books.size() % booksPerPage != 0) {
			pages = pages + 1;
		}
		
		List<BookDTO> books = new ArrayList<>();
		int count = 0;
		
		for(BookDTO b : this.books) {
			
			if(count <= this.booksPerPage) {
				books.add(b);
				count++;
			}
			
		}
		
		this.books = books;
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	
	public int getBooksPerPage() {
		return booksPerPage;
	}
	
	public void setBooksPerPage(int booksPerPage) {
		
		this.booksPerPage = booksPerPage;
		
	}
	
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String showBooks() {
		
		List<BookDTO> books = new ArrayList<>();
		int count = 0;
		
		for(BookDTO b : this.books) {
			
			if(count <= this.booksPerPage) {
				books.add(b);
				count++;
			}
			
		}
		
		this.books = books;
		
		return "";
	}
	
	public String nextPage() {
		
	
		books = bookService.getFullBooks();
		int skip = page * booksPerPage;		
		page++;
		
		pages = books.size() / booksPerPage;
		
		if(books.size() % booksPerPage != 0) {
			pages = pages + 1;
		}
		
		List<BookDTO> books = new ArrayList<>();
		int count = 0;
		int skiped = 0;
		
		for(BookDTO b : this.books) {
			
			if(skiped < skip) {
				skiped++;
				continue;
			}
			
			if(count <= this.booksPerPage) {
				books.add(b);
				count++;
			}
			
		}
		
		this.books = books;
		
		
		
		return "";
	}
	
	

}
