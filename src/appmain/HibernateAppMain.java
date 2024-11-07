package appmain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.user.User;

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
			currentSession.close();
			factory.close();
		}
		if(factory.isClosed()) {
			System.out.println("Factory is closed.");
		}
	}

	private static void insertOneUser(Session session) {
		User user = new User(101, "Email_Sample", "pass_sample");
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}
}
