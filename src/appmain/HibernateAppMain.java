package appmain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import service.e001.UserService;
import service.e002onetoone.OneToOneService;
import service.e003onetomany.OneToManyService;
import service.e004onetomany.ManyToManyService;
import util.session.SessionFactoryProvider;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
		SessionFactory factory = SessionFactoryProvider.getSessionFactory();
		if(factory.isOpen()) {
			System.out.println("Factory is open.");
			Session currentSession = factory.getCurrentSession();
			System.out.println(currentSession);
			oneEntity(factory.openSession());
			oneToOneEntity(factory.openSession());
			oneToManyEntity(factory.openSession());
			manyToManyEntity(factory.openSession());
			currentSession.close();
			factory.close();
		}
		if(factory.isClosed()) {
			System.out.println("Factory is closed.");
		}
	}

	private static void oneEntity(Session session) {
		UserService userService = new UserService();
		userService.insertUser(session);
		userService.fetchUser(session);
		session.close();
	}

	private static void oneToOneEntity(Session session) {
		OneToOneService oneToOneService = new OneToOneService();
		oneToOneService.insertOneToOneEntity(session);
		oneToOneService.fetchOneToOneEntity(session);
		session.close();
	}

	private static void oneToManyEntity(Session session) {
		OneToManyService oneToManyService = new OneToManyService();
		oneToManyService.insertOneToManyEntity(session);
		oneToManyService.fetchOneToManyEntity(session);
		session.close();
	}
	
	private static void manyToManyEntity(Session session) {
		ManyToManyService manyToManyService = new ManyToManyService();
		manyToManyService.insertManyToManyEntity(session);
		manyToManyService.fetchManyToManyEntity(session);
		session.close();
	}
}
