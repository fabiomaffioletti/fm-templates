package com.fm.template.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-security.xml", "/applicationContext-dao.xml", "/applicationContext-resources.xml", "/applicationContext-manager.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class UserManagerTest {
	
	@Autowired
	private UserManager userManager;
	
	@Test
	public void testLoadUserByUsername() {
		User user = (User) userManager.loadUserByUsername("user");
		assertNotNull(user);
		assertNotNull(user.getRoles());
		assertTrue(user.getRoles().size() > 0);
	}
	
	@Test
	@ExpectedException(UsernameNotFoundException.class)
	public void testLoadUnexistingUserByUsername() {
		userManager.loadUserByUsername("unexisting");
	}

}
