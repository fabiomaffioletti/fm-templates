package com.fm.template.routing;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.fm.template.multitenancy.dao.UserDAO;
import com.fm.template.multitenancy.model.User;
import com.fm.template.multitenancy.routing.MultiTenancyServerContextHolder;

@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class MultiTenancyDAOTest extends MultiTenancyBaseTest {
	@Autowired
	private UserDAO userDAO;

	@Test
	public void testGetAll() {
		MultiTenancyServerContextHolder.setMultiTenancyServer(multiServer1);
		List<User> users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi1:\n" + users);

		MultiTenancyServerContextHolder.setMultiTenancyServer(multiServer2);
		users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi2:\n" + users);

		MultiTenancyServerContextHolder.setMultiTenancyServer(multiServer3);
		users = userDAO.all();
		Assert.assertTrue(users.size() == 0);
		System.out.println("users in multi3:\n" + users);

		User user1 = new User("fabio");
		User user2 = new User("alberto");
		userDAO.save(user1);
		userDAO.save(user2);
		users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi3:\n" + users);
	}

	@Test(expected = RuntimeException.class)
	public void testRollback() {
		MultiTenancyServerContextHolder.setMultiTenancyServer(multiServer3);
		List<User> users = userDAO.all();
		Assert.assertTrue(users.size() == 0);
		System.out.println("users in multi3:\n" + users);

		User user3 = new User("rollback");
		userDAO.saveRollback(user3);

		users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi3:\n" + users);
	}

}