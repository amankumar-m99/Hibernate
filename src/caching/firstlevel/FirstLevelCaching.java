package caching.firstlevel;

import org.hibernate.Session;

import entity.e002onetoone.Country;
import util.session.SessionFactoryProvider;

public class FirstLevelCaching {

	public void showFirstLevelCaching() {
		Session session = SessionFactoryProvider.getSessionFactory().openSession();
		long id = 1;
		Country country1 = session.get(Country.class, id);//query fired here
		System.out.println(country1);

		Country country2 = session.get(Country.class, id);//query not fired here as object is cached in the session
		System.out.println(country2);
		System.out.println(session.contains(country2));//returns true;

		session.clear();//session cache got cleared here

		System.out.println(session.contains(country2));//returns false;
		Country country3 = session.get(Country.class, id);//query fired here
		System.out.println(country3);

	}
}
