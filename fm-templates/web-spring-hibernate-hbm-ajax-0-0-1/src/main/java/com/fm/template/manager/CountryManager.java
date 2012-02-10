package com.fm.template.manager;

import java.util.List;

import com.fm.template.exception.CountryNotFoundException;
import com.fm.template.model.Country;

public interface CountryManager {
	
	List<Country> getCountries();
	
	Country getCountryById(Integer id) throws CountryNotFoundException;

}