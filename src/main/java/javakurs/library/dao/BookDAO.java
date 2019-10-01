package javakurs.library.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javakurs.libary.domain.Book;
import javakurs.libary.domain.Category;

@Stateless
public class BookDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Book find(String title) {
		
		String q = "select b from Book b where b.title = :title";
		
		TypedQuery<Book> query = em.createQuery(q, Book.class);
		
		query.setParameter("title", title);
		
		try {
			
			return query.getSingleResult();
			
		} catch(NoResultException ex) {
			
			return null;
			
		}
		
	}
	
	public int save(Book book) {
		
		em.persist(book);		
		return book.getId();
		
	}
	
	public List<Book> listBooks(){
		
		String q = "select b from Book b";
		
		TypedQuery<Book> query = em.createQuery(q, Book.class);
		
		List<Book> results = query.getResultList();
		
		return results;
		
	}
	
	public Book find(int bookID) {
		
		return em.find(Book.class, bookID);
		
	}
	
	public void remove(Book book) {
		em.remove(book);
	}
	
	public List<Book> find(String title, String author, String isbn, Category category){
		
		String q = "select b from Book b where 1=1";
		
		if(title != null && !title.isEmpty()) {
			
			q += " and b.title like concat('%', :title, '%')";
		}
		
		if(author != null && !author.isEmpty()) {
			
			q += " and b.author like concat('%', :author, '%')";
			
		}
		
		if(isbn != null && !isbn.isEmpty()) {
			
			q += " and b.isbn = :isbn";
		}
		
		if(category != null) {
			
			q+= " and b.category = :category";
		}
		
		
		TypedQuery<Book> query = em.createQuery(q, Book.class);
		
		if(title != null && !title.isEmpty()) {
			query.setParameter("title", title);
		}
		
		if(author != null && !author.isEmpty()) {
			query.setParameter("author", author);
		}
		
		if(isbn != null && !isbn.isEmpty()) {
			query.setParameter("isbn", isbn);
		}
		
		if(category != null) {
			query.setParameter("category", category);
		}
		
		return query.getResultList();
		
	}

}
