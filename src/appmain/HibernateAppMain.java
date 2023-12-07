package appmain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Certificate;
import entity.Student;
import entity.onetomany.Author;
import entity.onetomany.Book;
import entity.onetoone.Answer;
import entity.onetoone.Question;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
//		SessionFactory factory = new Configuration().configure().buildSessionFactory(); // use this if config is standard and also present at standard location
		SessionFactory factory = new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		System.out.println(factory);
		System.out.println(factory.isClosed());
		
		Student student = new Student(101,"Aman","Lucknow");
		student.setCertificate(new Certificate("JavaFX", 5));
		Address address = new Address();
		address.setStreet("street_x");
		address.setCity("city_x");
		address.setPincode(241303);
		address.setDate(new Date());
		factory.openSession();
		
		Answer answer = new Answer(101,"this is ans");
		Question question = new Question(201,"this is question", answer);
		answer.setQuestion(question);

		Author author1 = new Author(201,"Author1", null);
		Author author2 = new Author(202,"Author2", null);
		Book book1 = new Book(101,"book1", author1, new Date());
		Book book2 = new Book(102,"book2", author2, new Date());
		Book book3 = new Book(103,"book3", author1, new Date());
		Book book4 = new Book(104,"book4", author2, new Date());
		Book book5 = new Book(105,"book5", author1, new Date());
		List<Book> list1 = new ArrayList<Book>();
		list1.add(book1);
		list1.add(book3);
		list1.add(book5);
		List<Book> list2 = new ArrayList<Book>();
		list2.add(book2);
		list2.add(book4);
		author1.setBooks(list1);
		author2.setBooks(list2);

		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
//		session.save(student);
//		session.save(address);
//		session.save(question);
//		session.save(answer);
		session.save(book1);
		session.save(book2);
		session.save(book3);
		session.save(book4);
		session.save(book5);
		session.save(author1);
		session.save(author2);
		System.out.println("Fetching objects...");
//		Student fetchedStudent = session.load(Student.class, 106);
		Student fetchedStudent = session.get(Student.class, 101);
		System.out.println(fetchedStudent);
//		Address fetchedAddress = session.load(Address.class, 1);
		Address fetchedAddress = session.get(Address.class, 1);
		System.out.println(fetchedAddress);
		
		transaction.commit();
		session.close();
	}
}
