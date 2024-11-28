package query.q03criteriaapi;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.e001.User;
import service.e001.UserService;
import util.session.SessionFactoryProvider;

public class CriteriaAPIExample {

	public void showExample() {
		Session session = SessionFactoryProvider.getSessionFactory().openSession();

		UserService userService = new UserService();
		userService.seedUser(session, 10);

		session.clear();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("address.state","U.P.1"));
		criteria.add(Restrictions.gt("id",4L));
		criteria.add(Restrictions.isNotNull("address.landmark"));
		criteria.add(Restrictions.like("address.city", "%oid%"));
		criteria.add(Restrictions.ilike("address.city", "%oid%"));//case sensitive

		List<User> users = criteria.list();
		for(User user:users) {
			System.out.println(user);
		}
	}
}
