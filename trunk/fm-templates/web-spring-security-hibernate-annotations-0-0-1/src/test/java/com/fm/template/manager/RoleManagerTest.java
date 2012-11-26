package com.fm.template.manager;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.model.Role;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RoleManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RoleManager roleManager;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		Assert.assertNotNull(roleManager);
	}

	@Test
	public void save() {
		Role role = new Role();
		role.setRoleName("testrole");
		role.setRoleDescription("testroledesc");
		roleManager.save(role, true);
	}

	@Test
	public void update() {
		Role role = roleManager.get(1);
		role.setRoleDescription("test");
		roleManager.update(role, true);
		sessionFactory.getCurrentSession().flush();
		role = roleManager.get(1);
	}

}