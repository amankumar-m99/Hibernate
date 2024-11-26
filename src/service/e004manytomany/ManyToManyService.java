package service.e004manytomany;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	public void fetchManyToManyEntity(Session session) {
		eagerLoadMoviesFromActor(session);
		eagerLoadActorsFromMovie(session);
		lazyLoadMoviesFromActor(session);
		lazyLoadActorsFromMovie(session);
	}

	private void eagerLoadMoviesFromActor(Session session) {
		session.clear();
		Actor actor = session.get(Actor.class, 1L);//query fired here but without movies
		System.out.println("Actor:"+actor.getName());
		List<Movie> movies = actor.getMovies();
		for(Movie movie:movies) {//query for movies fired here, on first iteration of loop
			System.out.println(movie.getTitle());
		}
	}

	private void eagerLoadActorsFromMovie(Session session) {
		session.clear();
		Movie movie = session.get(Movie.class, 1L);//query fired here but without actors
		System.out.println("Movie:"+movie.getTitle());
		List<Actor> actors = movie.getActors();
		for(Actor actor:actors) {//query for actors fired here, on first iteration of loop
			System.out.println(actor.getName());
		}
	}

	private void lazyLoadMoviesFromActor(Session session) {
		session.clear();
		Actor actor = session.load(Actor.class, 1L);//query not fired here
		System.out.println("Actor:"+actor.getName());//query fired here but without movies
		List<Movie> movies = actor.getMovies();
		for(Movie movie:movies) {//query for movies fired here, on first iteration of loop
			System.out.println(movie.getTitle());
		}
	}

	private void lazyLoadActorsFromMovie(Session session) {
		session.clear();
		Movie movie = session.load(Movie.class, 1L);//query not fired here
		System.out.println("Movie:"+movie.getTitle());//query fired here but without actors
		List<Actor> actors = movie.getActors();
		for(Actor actor:actors) {//query for actors fired here, on first iteration of loop
			System.out.println(actor.getName());
		}
	}
}
