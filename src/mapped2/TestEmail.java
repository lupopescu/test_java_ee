package mapped2;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;



public class TestEmail {

	SessionFactory factory;

	@Before
	public void setup() {
		try {
			
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			System.out.println("-----------------");
			ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
			srBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
			factory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("////////////");
			// saveMessage();
			testProperSimpleInversionCode(factory);
		} catch (HibernateException e) {
			System.out.println(e + "     hibernate exteption on setUp method");
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public void testProperSimpleInversionCode(SessionFactory factory) {
		Long emailId;
		Long messageId;
		Email email;
		Message message;

		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			email = new Email("Proper");
			message = new Message("Message of proper");

			email.setMessage(message);
			message.setEmail(email);

			session.save(email);
			session.save(message);

			emailId = email.getId();
			messageId = message.getId();
			session.persist(email);
			session.persist(message);
			tx.commit();

			assertNotNull(email.getMessage());
			assertNotNull(message.getEmail());
			session.close();
		} catch (HibernateException e) {
			System.out.println(e + "     hibernate exteption on save method");
		} catch (Exception e1) {
			System.out.println(e1 + "***********");
		}

	}

	// @Test
	public void saveMessage() {
		try {

			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Message message = new Message("Hello2, world");
			
			session.persist(message);
			tx.commit();
			// System.out.println(message.toString());
			assertEquals("Message {getId()=1, getText()=Hello2, world}",
					message.toString());
			session.close();
		} catch (HibernateException e) {
			System.out.println(e + "     hibernate exteption on save method");
		} catch (Exception e1) {
			System.out.println(e1 + "***********");
		}
	}

	@Test
	public void readEmail() {

		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Email> list = (List<Email>) session.createQuery("from Email2")
				.list();
		if (list.size() > 1) {
			Assert.fail("Email configuration in error; "
					+ "table should contain only one."
					+ " Set ddl to drop-create.");
		}
		if (list.size() == 0) {
			Assert.fail("Read of initial message failed; "
					+ "check saveEmail() for errors."
					+ " How did this test run?");
		}

		String checkEmail = "Email{id=1, subject='Proper', message.content=Message of proper}";
		String r = "";
		for (Email m : list) {
			System.out.println(m + "@@@@");
			r += m.toString();

		}
		assertEquals(checkEmail, r);
		session.close();
		System.err.println("read---Email------------------------------");
	}

	@Test
	public void readMessage1() {

		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Message> list = (List<Message>) session.createQuery(
				"from Message2").list();
		if (list.size() > 1) {
			Assert.fail("Message2 configuration in error; "
					+ "table should contain only one."
					+ " Set ddl to drop-create.");
		}
		if (list.size() == 0) {
			Assert.fail("Read of initial message failed; "
					+ "check saveMessage() for errors."
					+ " How did this test run?");
		}
		String checkEmail = "Message{id=1, content='Message of proper', email.subject=Proper}";
		String r = "";
		for (Message m : list) {
			System.out.println(m + ":))))");
			r += m.toString();

		}
		assertEquals(checkEmail, r);
	System.out.println("read-----Message----------------------------");
		session.close();
	}

	@Ignore
	@Test
	public void readMessage() {

		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Message> list = (List<Message>) session
				.createQuery("from Message").list();
		if (list.size() > 1) {
			Assert.fail("Message configuration in error; "
					+ "table should contain only one."
					+ " Set ddl to drop-create.");
		}
		if (list.size() == 0) {
			Assert.fail("Read of initial message failed; "
					+ "check saveMessage() for errors."
					+ " How did this test run?");
		}
		for (Message m : list) {
			System.out.println(m + "**");
		}
		session.close();
	}


}
