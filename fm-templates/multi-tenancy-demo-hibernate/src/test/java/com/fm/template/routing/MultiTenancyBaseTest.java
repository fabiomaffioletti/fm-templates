package com.fm.template.routing;

import org.junit.Before;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.fm.template.multitenancy.model.Tenant;

public class MultiTenancyBaseTest extends AbstractJUnit4SpringContextTests {
	
	protected Tenant multiServer1;
	protected Tenant multiServer2;
	protected Tenant multiServer3;
	
	@Before
	public void setUp() {
		multiServer1 = (Tenant) applicationContext.getBean("multiServer1");
		multiServer2 = (Tenant) applicationContext.getBean("multiServer2");
		multiServer3 = (Tenant) applicationContext.getBean("multiServer3");
	}

}
