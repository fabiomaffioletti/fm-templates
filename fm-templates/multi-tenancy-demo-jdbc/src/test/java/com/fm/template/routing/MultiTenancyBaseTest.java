package com.fm.template.routing;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.fm.template.multitenancy.routing.Tenant;
import com.fm.template.multitenancy.routing.MultiTenancyServerContextHolder;

public class MultiTenancyBaseTest extends AbstractJUnit4SpringContextTests {
	
	protected Tenant tenantServer1;
	protected Tenant tenantServer2;
	protected Tenant tenantServer3;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() {
		tenantServer1 = (Tenant) applicationContext.getBean("tenantServer1");
		tenantServer2 = (Tenant) applicationContext.getBean("tenantServer2");
		tenantServer3 = (Tenant) applicationContext.getBean("tenantServer3");
		
		MultiTenancyServerContextHolder.setTenant(tenantServer3);
		jdbcTemplate.update("truncate user");
	}

}
