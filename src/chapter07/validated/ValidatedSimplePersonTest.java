package chapter07.validated;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import lombok.Builder;
import email.SessionUtil;
import lombok.Setter;
import lombok.delombok.*;
public class ValidatedSimplePersonTest {

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("unused")
	private ValidatedSimplePerson persist(ValidatedSimplePerson person) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.persist(person);
		tx.commit();
		session.close();
		return person;
		}
	@Test
	public void createValidPerson() {
		
	persist(ValidatedSimplePerson.builder()
	.age(15)
	.fname("Johnny")
	.lname("McYoungster").build());
	}
	@Test
	public void createValidatedUnderagePerson() {
	persist(ValidatedSimplePerson.builder()
	.age(12)
	.fname("Johnny")
	.lname("McYoungster").build());
	fail("Should have failed validation");
	}
	@Test
	public void createValidatedPoorFNamePerson2() {
	persist(ValidatedSimplePerson.builder()
	.age(14)
	.fname("J")
	.lname("McYoungster2").build());
	fail("Should have failed validation");
	}
	@Test
	public void createValidatedNoFNamePerson() {
	persist(ValidatedSimplePerson.builder()
	.age(14)
	.lname("McYoungster2").build());
	fail("Should have failed validation");
	}

}
