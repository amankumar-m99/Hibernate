package caching.secondlevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.e001.User;
import util.session.SessionFactoryProvider;

public class SecondLevelCaching {

	public void showSecondLevelCaching() {
		SessionFactory factory = SessionFactoryProvider.getSessionFactory();
		
		Session session1 = factory.openSession();
		User user1 = session1.get(User.class, 1L);//query fired here
		System.out.println(user1);
		session1.close();

		Session session2 = factory.openSession();
		User user2 = session2.get(User.class, 1L);//query not fired here if cached in second level
		System.out.println(user2);
		session1.close();
	}
}
