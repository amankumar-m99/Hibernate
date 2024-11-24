package query.q02hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.e001.User;

public class HQLExample {

	public void fireHQLQueryExampleForUser(Session session) {
		System.out.println("Firing HQL...");
		exampleQuery1(session);
		exampleQuery2(session);
		exampleQuery3StringInWhere(session);
		exampleQuery4Parameter(session);
		exampleQuery5Alias(session);
	}

	private void exampleQuery1(Session session) {
		// HQL Syntax: from <Entity class name>
		String query = "from User";
		System.out.println("Firing query: " + query);
		Query<User> hqlQuery = session.createQuery(query);
		// hqlQuery.uniqueResult(); // for single object
		List<User> users = hqlQuery.list(); // for multiple objects
		printUsers(users);
	}

	private void exampleQuery2(Session session) {
		// HQL Syntax: from <Entity class name> where <condition>
		String query = "from User where id > 3";
		System.out.println("Firing query: " + query);
		Query<User> hqlQuery = session.createQuery(query);
		// hqlQuery.uniqueResult(); // for single object
		List<User> users = hqlQuery.list(); // for multiple objects
		printUsers(users);
	}

	private void exampleQuery3StringInWhere(Session session) {
		// HQL Syntax: from <Entity class name> where <condition>
		String query = "from User where city = 'Noida'";
		System.out.println("Firing query: " + query);
		Query<User> hqlQuery = session.createQuery(query);
		// hqlQuery.uniqueResult(); // for single object
		List<User> users = hqlQuery.list(); // for multiple objects
		printUsers(users);
	}

	private void exampleQuery4Parameter(Session session) {
		// HQL Syntax: from <Entity class name> where <condition>
		String query = "from User where city = :x";
		System.out.println("Firing query: " + query);
		Query<User> hqlQuery = session.createQuery(query);
		hqlQuery.setParameter("x", "Noida");
		// hqlQuery.uniqueResult(); // for single object
		List<User> users = hqlQuery.list(); // for multiple objects
		printUsers(users);
	}

	private void exampleQuery5Alias(Session session) {
		// HQL Syntax: from <Entity class name> where <condition>
		String query = "from User as u where u.address.city = :x and u.address.pincode = :y";
		System.out.println("Firing query: " + query);
		Query<User> hqlQuery = session.createQuery(query);
		hqlQuery.setParameter("x", "Noida");
		hqlQuery.setParameter("y", 201301);
		// hqlQuery.uniqueResult(); // for single object
		List<User> users = hqlQuery.list(); // for multiple objects
		printUsers(users);
	}

	private void printUsers(List<User> users) {
		for (User user : users) {
			System.out.println(user);
		}
		System.out.println("----------");
	}
}
