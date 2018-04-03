package com.redhat.osas.util.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import lombok.*;
public class ThingTest {

	@Test
	public void testEntityManager() {
		EntityManager em = JPASessionUtil.getEntityManager("utiljpa");
		em.getTransaction().begin();
		Thing t = new Thing();
		t.setName("Thing 1");
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		em = JPASessionUtil.getEntityManager("utiljpa");
		em.getTransaction().begin();
		Query q = em.createQuery("from Thing t where t.name=:name");
		q.setParameter("name", "Thing 1");
		Thing result = (Thing) q.getSingleResult();
		assertNotNull(result);
		assertEquals(result, t);
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testSession() {
		Session session = JPASessionUtil.getSession("utiljpa");
		Transaction tx = session.beginTransaction();
		Thing t = new Thing();
		t.setName("Thing 2");
		session.persist(t);
		tx.commit();
		session.close();
		session = JPASessionUtil.getSession("utiljpa");
		tx = session.beginTransaction();
		org.hibernate.Query q = session
				.createQuery("from Thing t where t.name=:name");
		q.setParameter("name", "Thing 2");
		Thing result = (Thing) q.uniqueResult();
		assertNotNull(result);
		assertEquals(result, t);
		session.delete(result);
		tx.commit();
		session.close();
	}
}