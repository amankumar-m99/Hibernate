package service.e003onetomany;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.e003onetomany.Author;
import entity.e003onetomany.Book;

public class OneToManyService {

	public void insertOneToManyEntity(Session session) {
		Author authorA = new Author("Aman", "K", "Maurya", new Date());
		Author authorN = new Author("Nawal", "K", "Maurya", new Date());
		Book book1 = new Book("isbn1", "Book1 by A", 2024, authorA);
		Book book2 = new Book("isbn2", "Book2 by A", 2024, authorA);
		Book book3 = new Book("isbn3", "Book1 by N", 2024, authorN);
		Book book4 = new Book("isbn4", "Book2 by N", 2024, authorN);
		Set<Book> booksA = new HashSet<>(Arrays.asList(book1, book2));
		Set<Book> booksN = new HashSet<>(Arrays.asList(book3, book4));
		authorA.setBooks(booksA);
		authorN.setBooks(booksN);
		Transaction transaction = session.beginTransaction();
		session.save(authorA);
		session.save(authorN);
		session.save(book1);
		session.save(book2);
		session.save(book3);
		session.save(book4);
		transaction.commit();
		session.close();
	}

	public void fetchOneToManyEntity(Session session) {
		long id = 1;
		System.out.println("Fetching author with id " + id + "....");
		Author author = session.get(Author.class, id);
		System.out.println("Fetched author:" + author.getFirstName());
		System.out.println("Fetching books of this author...");
		for(Book book:author.getBooks()) {
			System.out.println(book);
		}
	}
}
