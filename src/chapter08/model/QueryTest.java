package chapter08.model;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.constraints.AssertFalse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.*;


public class QueryTest {
SessionFactory factory;
	@Before
	public void populateData() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Supplier supplier = new Supplier("Hardware, Inc.");
		session.save(supplier);

		supplier = new Supplier("Supplier 2");
		
		session.save(supplier);
		tx.commit();
		session.close();
	}

	@After
	public void closeSession() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.createQuery("delete from Supplier").executeUpdate();
		tx.commit();
		session.close();
		 session = factory.openSession();
		 tx = session.beginTransaction();
		 
		 Supplier supplier1 = new Supplier();
		 @SuppressWarnings("unchecked")
		List<Supplier> list = (List<Supplier>) session.createQuery("from Supplier")
					.list();
		System.out.println( list.size());
		 
		tx.commit();
		session.close();
	}

	@Test
	public void testSuppliers() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Supplier supplier = (Supplier) session.get(Supplier.class, 1);
		tx.commit();
		session.close();

		session = factory.openSession();
		tx = session.beginTransaction();

		Supplier supplier1 = (Supplier) session.get(Supplier.class, 2);
		assertEquals(supplier1.getName(), "Supplier 2");
		assertEquals(supplier.getName(), "Hardware, Inc.");
		 @SuppressWarnings("unchecked")
			List<Supplier> list = (List<Supplier>) session.createQuery("from Supplier")
						.list();
			System.out.println( list.size());
		tx.commit();
		session.close();
	}
}
