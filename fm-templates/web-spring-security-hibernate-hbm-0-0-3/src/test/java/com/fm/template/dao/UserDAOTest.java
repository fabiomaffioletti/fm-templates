package com.fm.template.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.model.Role;
import com.fm.template.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-security.xml", "/applicationContext-dao.xml", "/applicationContext-resources.xml", "/applicationContext-manager.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testGetUserByUsername() {
		User user = userDAO.getUserByUsername("user");
		assertNotNull(user);
		assertEquals("user", user.getUsername());
		assertNotNull(user.getRoles());
		assertTrue(user.getRoles().size() == 1);
		Role userRole = user.getRoles().iterator().next();
		assertEquals("ROLE_USER", userRole.getRoleName());
	}
	
	@Test
	public void testGetUsers() {
		List<User> users = userDAO.getUsers();
		assertNotNull(users);
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testGetUserById() {
		User user = userDAO.getUserById(1);
		assertNotNull(user);
		user = userDAO.getUserById(999);
		assertNull(user);
	}
	
}