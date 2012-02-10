package com.fm.template.dao;

import java.util.List;

import com.fm.template.model.Country;

public interface CountryDAO {

	List<Country> getCountries();

	Country getCountryById(Integer id);

}
