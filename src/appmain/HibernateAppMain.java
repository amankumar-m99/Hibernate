package appmain;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Student;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
//		SessionFactory factory = new Configuration().configure().buildSessionFactory(); // use this if config is standard and also present at standard location
		SessionFactory factory = new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		System.out.println(factory);
		System.out.println(factory.isClosed());
		
		Student student = new Student(101,"Aman","Lucknow");
		System.out.println(student);

		Address address = new Address();
		address.setStreet("street_x");
		address.setCity("city_x");
		address.setPincode(241303);
		address.setDate(new Date());
		factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(student);
		session.save(address);
		transaction.commit();
		session.close();
	}
}
