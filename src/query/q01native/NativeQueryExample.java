package query.q01native;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class NativeQueryExample {

	public void fireNativeQueryExampleForUser(Session session) {
		String query = "select * from user";
		NativeQuery<Object[]> nativeQuery = session.createSQLQuery(query);

		List<Object[]> objects = nativeQuery.list();

		for(Object[] object : objects) {
			System.out.println(Arrays.toString(object));
		}
	}
}
