package com.fm.template.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-dao.xml", "/applicationContext-resources.xml", "/applicationContext-manager.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class CountryDAOTest {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Test
	public void testGetCountries() {
		List<Country> countries = countryDAO.getCountries();
		assertNotNull(countries);
		assertTrue(countries.size() == 4);
	}
	
	@Test
	public void testGetCountryById() {
		Country italy = countryDAO.getCountryById(1);
		assertNotNull(italy);
		assertTrue(italy.getCities().size() == 3);
		
		Country australia = countryDAO.getCountryById(3);
		assertNotNull(australia);
		assertTrue(australia.getCities().size() == 4);
	}
	
}