package com.fm.template.manager;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.fm.template.model.Config;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HibernateTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test(expected = NonUniqueObjectException.class)
	public void testMergeSaveUpdate() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Config item = (Config) session.get(Config.class, new Integer(1));
		tx.commit();
		session.close(); // end of first session, item is detached

		item.getId(); // The database identity is "1"
		item.setConfigValue("test");
		Session session2 = sessionFactory.openSession();
		Transaction tx2 = session2.beginTransaction();
		Config item2 = (Config) session2.get(Config.class, new Integer(1));
		session2.update(item); // Throws NonUniqueObjectException
		tx2.commit();
		session2.close();
	}

	@Test
	public void testMergeSaveUpdate_2() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Config item = (Config) session.get(Config.class, new Integer(1));
		tx.commit();
		session.close(); // end of first session, item is detached

		item.getId(); // The database identity is "1"
		item.setConfigValue("test");
		Session session2 = sessionFactory.openSession();
		Transaction tx2 = session2.beginTransaction();
		Config item2 = (Config) session2.get(Config.class, new Integer(1));
		Config item3 = (Config) session2.merge(item); // Success!
		tx2.commit();
		session2.close();
	}

}