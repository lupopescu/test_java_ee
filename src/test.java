import static org.junit.Assert.*;

import mesage.Message;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import email.Email;
import email.SessionUtil;



public class test {
SessionFactory factory;
@Before
public void setUp(){
	factory=(SessionFactory) SessionUtil.getSession();
	System.out.println("**************************");
}
	 @Test
	    public void testProperSimpleInversionCode() {
	        Long emailId;
	        Long messageId;
	        Email email;
	        Message message;
System.out.println("------------------------------------");
	       Session session = factory.openSession();
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

	       Session session2 =  factory.openSession();
	            email = (Email) session2.get(Email.class, emailId);
	            System.out.println(email);
	            message = (Message) session2.get(Message.class, messageId);
	            System.out.println(message);
	       

	        assertNotNull(email.getMessage());
	        assertNotNull(message.getEmail());
	    }

	    @Test
	    public void testBrokenInversionCode() {
	        Long emailId;
	        Long messageId;

	        Session session =  factory.openSession();
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

	        session =  factory.openSession();
	        tx = session.beginTransaction();
	        email = (Email) session.get(Email.class, emailId);
	        System.out.println(email);
	        message = (Message) session.get(Message.class, messageId);
	        System.out.println(message);

	        tx.commit();
	        session.close();

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

	     

	        assertEquals(email.getSubject(), "Inverse Email");
	        assertEquals(message.getContent(), "Inverse Message");
	        assertNull(email.getMessage());
	        assertNotNull(message.getEmail());

	       Session session2 = SessionUtil.getSession();
	            email = (Email) session2.get(Email.class, emailId);
	            System.out.println(email);
	            message = (Message) session2.get(Message.class, messageId);
	            System.out.println(message);
	       

	        assertNotNull(email.getMessage());
	        assertNotNull(message.getEmail());
	    }

	}
