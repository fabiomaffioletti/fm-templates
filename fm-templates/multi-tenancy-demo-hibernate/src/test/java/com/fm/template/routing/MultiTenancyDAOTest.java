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
		MultiTenancyServerContextHolder.setMultiTenancyServer(tenantServer1);
		List<User> users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi1:\n" + users);

		MultiTenancyServerContextHolder.setMultiTenancyServer(tenantServer2);
		users = userDAO.all();
		Assert.assertTrue(users.size() == 2);
		System.out.println("users in multi2:\n" + users);
		
		MultiTenancyServerContextHolder.setMultiTenancyServer(tenantServer3);
		users = userDAO.all();
		Assert.assertTrue(users.size() == 0);
		System.out.println("users in multi3:\n" + users);

	}

}