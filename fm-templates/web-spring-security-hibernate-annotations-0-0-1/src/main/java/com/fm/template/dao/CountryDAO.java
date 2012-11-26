package com.fm.template.dao;

import java.util.List;

import com.fm.template.model.Country;

public interface CountryDAO extends GenericDAO<Country, Integer> { 

	List<Country> getByName(String name);
	
}
