package chapter08.model;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.PessimisticLockException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import email.SessionUtil;


public class TransactionExample {
    // note lack of @Test annotation
//    public void thisIsNotATest() {
//     Session session = SessionUtil.getSession();
//            Transaction tx = null;
//            try {
//                tx = session.beginTransaction();
//                // normal session usage here
//
//                tx.commit();
//            } catch (HibernateException e) {
//                if (tx != null && tx.isActive()) {
//                    tx.rollback();
//                }
//            }
//       
//    }
//
//    @Test
//    public void demonstrateRollback() {
//       Session session = SessionUtil.getSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("delete from Publisher");
//            query.executeUpdate();
//            tx.commit();
//       
//       Session session2 = SessionUtil.getSession();
//            Query query1 =(Publisher) session2.createQuery("from Publisher", Publisher.class);
//            assertEquals(query1.list().size(), 0);
//      
//      Session session3 = SessionUtil.getSession();
//            Transaction tx1 = session3.beginTransaction();
//            Publisher publisher = new Publisher();
//            publisher.setName("foo");
//            session3.save(publisher);
////            tx.rollback();
//     
//      Session session4 = SessionUtil.getSession();
//            Query<Publisher> query2 = session4.createQuery("from Publisher", Publisher.class);
//            assertEquals(query2.list().size(), 0);
//       
//    }
//
//    @Test
//    public void showDeadlock() throws InterruptedException {
//        Long publisherAId;
//        Long publisherBId;
//
//        //clear out old data and populate tables
//    Session session = SessionUtil.getSession();
//            Transaction tx = session.beginTransaction();
//            session.createQuery("delete from Publisher").executeUpdate();
//
//            Publisher publisher = new Publisher();
//            publisher.setName("A");
//            session.save(publisher);
//            publisherAId = publisher.getId();
//
//            publisher = new Publisher();
//            publisher.setName("B");
//            session.save(publisher);
//            publisherBId = publisher.getId();
//            tx.commit();
//        
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.submit(() -> updatePublishers("session1", publisherAId, publisherBId));
//        executor.submit(() -> updatePublishers("session2", publisherBId, publisherAId));
//        executor.shutdown();
//
//        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
//            executor.shutdownNow();
//            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
//                System.out.println("Executor did not terminate");
//            }
//       
//   Session session2 = SessionUtil.getSession();
//            Query<Publisher> query4 = session2.createQuery("from Publisher p order by p.name", Publisher.class);
//            String result = query4.list().stream().map(Publisher::getName).collect(Collectors.joining(","));
//            assertEquals(result, "A,B");
//        }
//    }
//
//    private void updatePublishers(String prefix, Long... ids) {
//        try {Session session = SessionUtil.getSession();
//            Transaction tx = session.beginTransaction();
//            for (Long id : ids) {
//                Thread.sleep(100);
//                Publisher publisher = session
//                        .byId(Publisher.class)
//                        .load(id);
//                publisher.setName(prefix + " " + publisher.getName());
//            }
//            tx.commit();
//        } catch (InterruptedException | PessimisticLockException e) {
//            e.printStackTrace();
//        }
//    }
}
