package service.e002onetoone;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.e002onetoone.Capital;
import entity.e002onetoone.Country;

public class OneToOneService {

	public void insertOneToOneEntity(Session session) {
		Capital capital = new Capital(1, "New Delhi", 2001);
		Country country = new Country(1, "India", "ind", 91);
		country.setCapital(capital);
		capital.setCountry(country);
		Transaction transaction = session.beginTransaction();
		session.save(country);
		session.save(capital);
		transaction.commit();
	}

	public void fetchOneToOneEntity(Session session) {
		session.clear();
		Country country;
		Capital capital;
		// eager load
		country = session.get(Country.class, 1L);// query fired here
		System.out.println(country.getName());
		session.clear();
		// lazy load
		country = session.load(Country.class, 1L);// query not fired here
		System.out.println(country.getName());// query fired here
		capital = country.getCapital();
		System.out.println(capital.getName());
	}
}
