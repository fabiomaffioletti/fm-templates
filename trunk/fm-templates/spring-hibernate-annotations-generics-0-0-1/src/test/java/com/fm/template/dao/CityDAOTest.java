package com.fm.template.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.model.City;

@ContextConfiguration(locations={"/applicationContext-dao.xml"})
public class CityDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CityDao cityDAO;
	
	@Test
	public void testGetAll() {
		List<City> cities = cityDAO.getAll();
		Assert.assertTrue(cities.size() > 0);
	}
	
	@Test
	public void testGetById() {
		City city = cityDAO.get(1);
		Assert.assertNotNull(city);
		Assert.assertNotNull(city.getName());
		Assert.assertNotNull(city.getCountry());
	}
		
}