package com.fm.template.routing;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.lumata.multitenancy.routing.MultiTenancyServer;
import com.lumata.multitenancy.routing.MultiTenancyServerContextHolder;

public class MultiTenancyBaseTest extends AbstractJUnit4SpringContextTests {
	
	protected MultiTenancyServer multiServer1;
	protected MultiTenancyServer multiServer2;
	protected MultiTenancyServer multiServer3;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() {
		multiServer1 = (MultiTenancyServer) applicationContext.getBean("multiServer1");
		multiServer2 = (MultiTenancyServer) applicationContext.getBean("multiServer2");
		multiServer3 = (MultiTenancyServer) applicationContext.getBean("multiServer3");
		
		MultiTenancyServerContextHolder.setMultiTenancyServer(multiServer3);
		jdbcTemplate.update("truncate user");
	}

}
