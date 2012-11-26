package com.fm.template.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.CountryDAO;
import com.fm.template.model.Country;

@Repository(value="countryDAO")
public class CountryDAOImpl extends GenericDAOImpl<Country, Integer> implements CountryDAO {

	public CountryDAOImpl() {
		super(Country.class);
	}

	@Override
	public List<Country> getByName(String name) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name);
		return findByNamedQuery("Country.getByName", queryParams);
	}

}
