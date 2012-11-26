package com.fm.template.manager;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.model.City;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CityManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CityManager cityManager;

	@Before
	public void setUp() {
		Assert.assertNotNull(cityManager);
	}

	@Test
	public void testGetAll() {
		List<City> items = cityManager.all();
		Assert.assertTrue(items.size() > 0);
	}

}