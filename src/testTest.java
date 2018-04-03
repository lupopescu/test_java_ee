import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;


public class testTest {

	@Before
	public void setUp() throws Exception {
		
			System.out.println("***");
		EntityManager em = JPASessionUtil.getEntityManager("utiljpa");
		assertNotNull(em);
		System.out.println("////");
	}

	@Test
	public void getEntityManager() {
		System.out.println("***");
	EntityManager em = JPASessionUtil.getEntityManager("utiljpa");
	System.out.println(" toString");
	em.close();
	}
	@Test//(expected = {javax.persistence.PersistenceException.class})
	public void nonexistentEntityManagerName() {
	JPASessionUtil.getEntityManager("nonexistent");
	fail("We shouldn't be able to acquire an EntityManager here");
	}
	@Test
	public void getSession() {
	Session session = JPASessionUtil.getSession("utiljpa");
	session.close();
	}
}
