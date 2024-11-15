package appmain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.e001.Address;
import entity.e001.User;
import entity.e002onetoone.Capital;
import entity.e002onetoone.Country;
import entity.e003onetomany.Author;
import entity.e003onetomany.Book;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
		SessionFactory factory = new Configuration()
				//.configure() //use this if config file name is standard and also present at sibling to package
				.configure("resources/hibernate.cfg.xml") //use this if config file name is not standard or not present at sibling to package
				.buildSessionFactory();

		if(factory.isOpen()) {
			System.out.println("Factory is open.");
			Session currentSession = factory.getCurrentSession();
			System.out.println(currentSession);
			insertOneUser(currentSession);
			fetchUsers(factory.openSession());
			insertCountryAndCapital(factory.openSession());
			insertAuthorAndBooks(factory.openSession());
			fetchAuthorAndBooks(factory.openSession());
			currentSession.close();
			factory.close();
		}

		if(factory.isClosed()) {
			System.out.println("Factory is closed.");
		}
	}

	private static void insertOneUser(Session session) {
		User user = new User("Email_Sample", "pass_sample");
		user.setDateOfCreation(new Date());
		user.setAge(24);
		user.setAddress(new Address("Noida", "U.P.", "Sample_Landmark", 201301));
		user.setProfilePic(getSampleImage());
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	private static byte[] getSampleImage() {
		try (FileInputStream fis = new FileInputStream("src/resources/sample_image.png")) {
			byte[] data = new byte[fis.available()];
			fis.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void fetchUsers(Session session) {
		User user;
		user = session.load(User.class, 1L);
		System.out.println(user);
		user = session.get(User.class, 2L);
		System.out.println(user);
		session.close();
	}

	private static void insertCountryAndCapital(Session session) {
		Capital capital = new Capital(1, "New Delhi", 2001);
		Country country = new Country(1, "India", "ind", 91);
		country.setCapital(capital);
		capital.setCountry(country);
		Transaction transaction = session.beginTransaction();
		session.save(country);
		session.save(capital);
		transaction.commit();
	}

	private static void insertAuthorAndBooks(Session session) {
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
	}

	private static void fetchAuthorAndBooks(Session session) {
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
