package service.e001;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.e001.Address;
import entity.e001.User;

public class UserService {

	public void seedUser(Session session, int count) {
		for(int i=0; i<count; i++) {
			User user = new User("UserEmail"+i, "userPassword"+i);
			user.setDateOfCreation(new Date());
			user.setAge(i);
			user.setAddress(new Address("Noida", "U.P."+(i%2), "Sample_Landmark", 201301+(i%2)));
			user.setProfilePic(getSampleImage());
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		}
	}

	public void insertUser(Session session) {
		seedUser(session, 1);
	}

	private byte[] getSampleImage() {
		try (FileInputStream fis = new FileInputStream("src/resources/sample_image.png")) {
			byte[] data = new byte[fis.available()];
			fis.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void fetchUser(Session session) {
		session.clear();
		User user;
		//using get() method for eager load
		user = session.get(User.class, 2L);//returns null if not found
		System.out.println(user);
		// using load() method for lazy load
		user = session.load(User.class, 1L);//returns ObjectNotFoundException if not found
		System.out.println(user);
	}
}
