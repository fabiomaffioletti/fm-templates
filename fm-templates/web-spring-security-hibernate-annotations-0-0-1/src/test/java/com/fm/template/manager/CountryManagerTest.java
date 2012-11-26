package com.fm.template.manager;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.model.City;
import com.fm.template.model.Country;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CountryManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CountryManager countryManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		Assert.assertNotNull(countryManager);
	}

	@Test
	public void testAll() {
		List<Country> items = countryManager.all();
		Assert.assertTrue(items.size() > 0);
	}
	
	@Test
	public void testGet() {
		Country item = countryManager.get(1);
		Assert.assertNotNull(item);
	}
	
	@Test
	public void testMergeCities() {
		Country item = countryManager.get(1);
		sessionFactory.getCurrentSession().flush();
		Assert.assertNotNull(item);
		
		City city = new City();
		city.setCreatedAt(new Date());
		city.setModifiedAt(new Date());
		city.setCreatedBy("sys");
		city.setModifiedBy("sys");
		city.setName("test");
		item.addCity(city);
		
		item.setDescription("test");
		
		countryManager.merge(item, true);
		sessionFactory.getCurrentSession().flush();
	}

}