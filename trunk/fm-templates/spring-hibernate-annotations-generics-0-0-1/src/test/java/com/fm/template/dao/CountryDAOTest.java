package com.fm.template.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.model.Country;

@ContextConfiguration(locations={"/applicationContext-dao.xml"})
public class CountryDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CountryDao countryDAO;
	
	@Test
	public void testGetAll() {
		List<Country> countries = countryDAO.getAll();
		Assert.assertTrue(countries.size() > 0);
	}
	
	@Test
	public void testGetById() {
		Country country = countryDAO.get(1);
		Assert.assertNotNull(country);
		Assert.assertNotNull(country.getName());
		Assert.assertTrue(country.getCities().size() > 0);
	}
	
	@Test
	public void testGetByName() {
		List<Country> countries = countryDAO.getByName("italy");
		Assert.assertNotNull(countries);
		Assert.assertTrue(countries.size() == 1);
	}
	
	@Test
	public void saveCountry() {
		Country country = new Country();
		country.setName("france");
		countryDAO.save(country);
		Assert.assertNotNull(countryDAO.getByName("france"));
	}
	
	@Test(expected=ObjectRetrievalFailureException.class)
	public void removeCountry() {
		countryDAO.remove(1);
		countryDAO.get(1);
	}
	
}