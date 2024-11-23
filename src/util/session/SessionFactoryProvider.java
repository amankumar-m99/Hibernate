package util.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			factory = new Configuration()
					// .configure() //use this if config file name is standard and also present at sibling to package
					.configure("config/hibernate.cfg.xml") // use this if config file name is not standard or not present at sibling to package
					.buildSessionFactory();
		}
		return factory;
	}
}
