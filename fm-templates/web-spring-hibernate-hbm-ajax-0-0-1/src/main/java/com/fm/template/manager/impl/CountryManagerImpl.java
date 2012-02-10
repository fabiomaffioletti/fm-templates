package com.fm.template.manager.impl;

import java.util.List;

import com.fm.template.dao.CountryDAO;
import com.fm.template.exception.CountryNotFoundException;
import com.fm.template.manager.CountryManager;
import com.fm.template.model.Country;

public class CountryManagerImpl implements CountryManager {
	private CountryDAO countryDAO;
	
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	@Override
	public List<Country> getCountries() {
		return countryDAO.getCountries();
	}

	@Override
	public Country getCountryById(Integer id) throws CountryNotFoundException {
		Country country = countryDAO.getCountryById(id);
		if(country != null) {
			return country;
		} else {
			throw new CountryNotFoundException();
		}
	}

}
