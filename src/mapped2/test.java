package mapped2;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import email.SessionUtil;

public class test extends TestCase {

	@Test
	public void testProperSimpleInversionCode() {
		Long emailId;
		Long messageId;
		Email email;
		Message message;

		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		email = new Email("Proper");
		message = new Message("Proper");

		email.setMessage(message);
		message.setEmail(email);

		session.save(email);
		session.save(message);

		emailId = email.getId();
		messageId = message.getId();

		tx.commit();

		assertNotNull(email.getMessage());
		assertNotNull(message.getEmail());
		session.close();
		Session session2 = SessionUtil.getSession();
		//email = (Email) session2.get(Email.class, emailId);
		System.out.println(email + "eeeeeeeeeeeeeeeeeeeee");
		message = (Message) session2.get(Message.class, messageId);
		System.out.println(message + "****************************");
		session2.close();
		assertNotNull(email.getMessage());
		assertNotNull(message.getEmail());
	}

	@Test()
	public void testBrokenInversionCode() {
		System.err.println("testBrokenInversionCode()----------------");
		Long emailId;
		Long messageId;

		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Email email = new Email("Broken");
		Message message = new Message("Broken");

		email.setMessage(message);
		message.setEmail(email);

		session.save(email);
		session.save(message);

		emailId = email.getId();
		messageId = message.getId();

		tx.commit();
		session.close();

		assertNotNull(email.getMessage());
		assertNull(message.getEmail());

		Session session2 = SessionUtil.getSession();
		tx = session2.beginTransaction();
		email = (Email) session2.get(Email.class, emailId);
		System.out.println(email);
		message = (Message) session2.get(Message.class, messageId);
		System.out.println(message);

		tx.commit();
		session2.close();

		assertNull(email.getMessage());
		assertNull(message.getEmail());
	}

	@Test
	public void testImpliedRelationship() {
		Long emailId;
		Long messageId;
		Email email;
		Message message;

		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		email = new Email("Inverse Email");
		message = new Message("Inverse Message");

		email.setMessage(message);
		message.setEmail(email);

		session.save(email);
		session.save(message);

		emailId = email.getId();
		messageId = message.getId();

		tx.commit();

		assertEquals(email.getSubject(), "Inverse Email");
		assertEquals(message.getContent(), "Inverse Message");
		assertNull(email.getMessage());
		assertNotNull(message.getEmail());
		session.close();
		Session session2 = SessionUtil.getSession();
		email = (Email) session2.get(Email.class, emailId);
		System.err.println(email);
		message = (Message) session2.get(Message.class, messageId);
		System.err.println(message);
		session2.close();
		assertNotNull(email.getMessage());
		assertNotNull(message.getEmail());
	}

}
