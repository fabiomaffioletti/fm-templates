package com.fm.template.routing;

import org.junit.Before;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.fm.template.multitenancy.model.Tenant;

public class MultiTenancyBaseTest extends AbstractJUnit4SpringContextTests {
	
	protected Tenant tenantServer1;
	protected Tenant tenantServer2;
	protected Tenant tenantServer3;
	
	@Before
	public void setUp() {
		tenantServer1 = (Tenant) applicationContext.getBean("tenantServer1");
		tenantServer2 = (Tenant) applicationContext.getBean("tenantServer2");
		tenantServer3 = (Tenant) applicationContext.getBean("tenantServer3");
	}

}
