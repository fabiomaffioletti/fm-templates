package com.fm.template.global;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.multitenancy.dao.TenantDAO;
import com.fm.template.multitenancy.model.Tenant;
import com.fm.template.multitenancy.routing.MultiTenancyServerContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class MultiTenancyServerDAOTest {

	@Autowired
	private TenantDAO multiTenancyServerDAO;
	
	@Before
	public void setUp() {
		MultiTenancyServerContextHolder.clearMultiTenancyServerContext();
	}
	
	@Test
	@Transactional("globalTransactionManager")
	public void all() {
		Assert.assertNull(MultiTenancyServerContextHolder.getMultiTenancyServer());
		List<Tenant> servers = multiTenancyServerDAO.all();
		Assert.assertNotNull(servers);
		Assert.assertEquals(3, servers.size());
	}
	
	@Test
	@Transactional("globalTransactionManager")
	public void getById() {
		Tenant server = multiTenancyServerDAO.get(1);
		Assert.assertNotNull(server);
	}
	
	@Test
	@Transactional("globalTransactionManager")
	public void getByName() {
		Tenant server = multiTenancyServerDAO.get("server1");
		Assert.assertNotNull(server);
		Assert.assertEquals("jndi/multi_1", server.getJndiName());
	}
	
}
