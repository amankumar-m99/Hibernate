package appmain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.x.protobuf.MysqlxConnection.Capabilities;

import entity.e001user.User;
import entity.e002address.Address;
import entity.e003onetoone.Capital;
import entity.e003onetoone.Country;

public class HibernateAppMain {
	public static void main(String[] args) {
		System.out.println("Initiating Hibernate....");
		SessionFactory factory = new Configuration()
				//.configure() //use this if config file name is standard and also present at sibling to package
				.configure("resources/hibernate.cfg.xml") //use this if config file name is not standard or not present at sibling to package
				.buildSessionFactory();

		if(factory.isOpen()) {
			System.out.println("Factory is open.");
			Session currentSession = factory.getCurrentSession();
			System.out.println(currentSession);
			insertOneUser(currentSession);
			fetchUsers(factory.openSession());
			insertCountryAndCapital(currentSession);
			currentSession.close();
			factory.close();
		}

		if(factory.isClosed()) {
			System.out.println("Factory is closed.");
		}
	}

	private static void insertOneUser(Session session) {
		User user = new User("Email_Sample", "pass_sample");
		user.setDateOfCreation(new Date());
		user.setAge(24);
		user.setAddress(new Address("Noida", "U.P.", "Sample_Landmark", 201301));
		user.setProfilePic(getSampleImage());
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	private static byte[] getSampleImage() {
		try (FileInputStream fis = new FileInputStream("src/resources/sample_image.png")) {
			byte[] data = new byte[fis.available()];
			fis.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void fetchUsers(Session session) {
		User user;
		user = session.load(User.class, 1L);
		System.out.println(user);
		user = session.get(User.class, 2L);
		System.out.println(user);
		session.close();
	}

	private static void insertCountryAndCapital(Session session) {
		Capital capital = new Capital(1, "New Delhi", 2001);
		Country country = new Country(1, "India", "ind", 91);
		country.setCapital(capital);
		capital.setCountry(country);
		Transaction transaction = session.beginTransaction();
		session.save(country);
		session.save(capital);
		transaction.commit();
	}
}
