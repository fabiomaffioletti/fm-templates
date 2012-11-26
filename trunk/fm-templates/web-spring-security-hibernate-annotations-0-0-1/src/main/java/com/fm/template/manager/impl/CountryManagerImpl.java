package com.fm.template.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.dao.CountryDAO;
import com.fm.template.manager.CountryManager;
import com.fm.template.model.Country;

@Service(value="countryManager")
public class CountryManagerImpl extends GenericManagerImpl<Country, Integer> implements CountryManager {
	private CountryDAO countryDAO;
	
	@Autowired
	public CountryManagerImpl(CountryDAO countryDAO) {
		super();
		this.dao = countryDAO;
		this.countryDAO = countryDAO;
	}

}
