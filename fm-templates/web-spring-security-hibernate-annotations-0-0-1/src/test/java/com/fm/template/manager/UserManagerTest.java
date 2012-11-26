package com.fm.template.manager;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.builder.UserBuilder;
import com.fm.template.model.User;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		Assert.assertNotNull(userManager);
		User user = UserBuilder.userBuilder(2).id(1).username("admin").password("admin").build();
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "admin"));
	}

	@Test
	public void testGetAll() {
		List<User> users = userManager.all();
		Assert.assertTrue(users.size() > 0);
		for (User user : users) {
			Assert.assertTrue(user.getRoles().size() > 0);
		}
	}

	@Test
	public void testGet() {
		User user = userManager.get(1);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getUsername());
	}

	@Test
	public void testProperty() {
		User user = userManager.filter("username", "admin");
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getUsername());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadByUsername() {
		UserDetails user = userManager.loadUserByUsername("admin");
		Assert.assertNotNull(user);
		user = userManager.loadUserByUsername("unexisting");
	}

	@Test
	public void testSave() {
		User user = UserBuilder.userBuilder(1).username("test").email("email@gmail.com").password("test").build();
		user = userManager.get(userManager.save(user, true));
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getUsername());
		Assert.assertEquals(new Integer(0), user.getUserLoginStatistic().getLoginOk());
		Assert.assertNotNull(user.getRoles());
		Assert.assertTrue(user.getRoles().size() > 0);
	}
	
	@Test
	public void testUpdate() {
		User user = userManager.get(1);
		Assert.assertNotNull(user);
		Assert.assertEquals(new Integer(0), user.getLoginOk());
		Assert.assertEquals(new Integer(0), user.getLoginKo());
		user.incrementLoginOk();
		userManager.update(user, true);
		sessionFactory.getCurrentSession().flush();
		user = userManager.get(1);
		Assert.assertEquals(new Integer(1), user.getLoginOk());
		Assert.assertEquals(new Integer(0), user.getLoginKo());
	}
	
	@Test
	public void testDelete1() {
		User user = userManager.get(1);
		Assert.assertNotNull(user);
		userManager.remove(1);
		sessionFactory.getCurrentSession().flush();
		user = userManager.get(1);
		Assert.assertNull(user);
	}
	
	@Test
	public void testDelete2() {
		User user = userManager.get(1);
		Assert.assertNotNull(user);
		userManager.remove(user);
		sessionFactory.getCurrentSession().flush();
		user = userManager.get(1);
		Assert.assertNull(user);
	}

}