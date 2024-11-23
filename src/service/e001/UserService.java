package service.e001;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.e001.Address;
import entity.e001.User;

public class UserService {

	public void insertUser(Session session) {
		User user = new User("Email_Sample", "pass_sample");
		user.setDateOfCreation(new Date());
		user.setAge(24);
		user.setAddress(new Address("Noida", "U.P.", "Sample_Landmark", 201301));
		user.setProfilePic(getSampleImage());
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
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
		User user;
		// using load() method
		user = session.load(User.class, 1L);
		System.out.println(user);
		//using get() method
		user = session.get(User.class, 2L);
		System.out.println(user);
	}

}
