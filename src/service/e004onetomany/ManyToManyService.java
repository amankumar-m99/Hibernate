package service.e004onetomany;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.e004manytomany.Actor;
import entity.e004manytomany.Movie;

public class ManyToManyService {

	public void insertManyToManyEntity(Session session) {
		Actor a1 = new Actor("Irrfan", new Date());
		Actor a2 = new Actor("Deepika", new Date());
		Movie m1 = new Movie("Madari", 220, 2018);
		Movie m2 = new Movie("Piku", 220, 2019);
		a1.setMovies(Arrays.asList(m1, m2));
		a2.setMovies(Arrays.asList(m2));
		m1.setActors(Arrays.asList(a1));
		m2.setActors(Arrays.asList(a1,a2));
		Transaction transaction = session.beginTransaction();
		session.save(a1);
		session.save(a2);
		session.save(m1);
		session.save(m2);
		transaction.commit();
	}
}
