package appmain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import caching.firstlevel.FirstLevelCaching;
import caching.secondlevel.SecondLevelCaching;
import query.q01native.NativeQueryExample;
import query.q02hql.HQLExample;
import query.q02hql.HQLPagination;
import service.e001.UserService;
import service.e002onetoone.OneToOneService;
import service.e003onetomany.OneToManyService;
import service.e004manytomany.ManyToManyService;
import util.session.SessionFactoryProvider;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
		SessionFactory factory = SessionFactoryProvider.getSessionFactory();
		if(factory.isOpen()) {
			System.out.println("Factory is open.");
			Session currentSession = factory.getCurrentSession();
			System.out.println(currentSession);
			currentSession.close();

			//uncomment one method a time to study better
			//method start

//			oneEntity(factory.openSession());
//			oneToOneEntity(factory.openSession());
//			oneToManyEntity(factory.openSession());
//			manyToManyEntity(factory.openSession());
//			showNativeQueries(factory.openSession());
//			showHQLQuery(factory.openSession());
//			showPaginationUsingHQL(factory.openSession());
//			showFirstLevelCaching(factory.openSession());
//			showSecondLevelCaching(factory.openSession());

			//method end

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

	private static void showNativeQueries(Session session) {
		UserService userService = new UserService();
		userService.seedUser(session, 10);
		session.clear();
		new NativeQueryExample().fireNativeQueryExampleForUser(session);
		session.close();
	}

	private static void showHQLQuery(Session session){
		UserService userService = new UserService();
		userService.seedUser(session, 10);
		session.clear();
		new HQLExample().fireHQLQueryExampleForUser(session);
		session.close();
	}

	private static void showPaginationUsingHQL(Session session) {
		UserService userService = new UserService();
		userService.seedUser(session, 10);
		session.clear();
		new HQLPagination().paginationExample(session);
		session.close();
	}

	private static void showFirstLevelCaching(Session session) {
		OneToOneService oneToOneService = new OneToOneService();
		oneToOneService.insertOneToOneEntity(session);//to seed some data beforehand
		session.close();
		new FirstLevelCaching().showFirstLevelCaching();
	}

	private static void showSecondLevelCaching(Session session) {
		UserService userService = new UserService();
		userService.seedUser(session, 10);
		session.close();
		new SecondLevelCaching().showSecondLevelCaching();
	}
}
