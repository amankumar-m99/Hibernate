package query.q02hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.e001.User;

public class HQLPagination {

	public void paginationExample(Session session) {
		Query<User> query = session.createQuery("from User", User.class);
		query.setFirstResult(3);
		query.setMaxResults(5);
		List<User> users = query.list();
		printUsers(users);
		
	}

	private void printUsers(List<User> users) {
		for (User user : users) {
			System.out.println(user);
		}
		System.out.println("----------");
	}
}
