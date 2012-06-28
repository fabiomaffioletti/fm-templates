package com.fm.template.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.CountryDao;
import com.fm.template.model.Country;

@Repository(value="countryDao")
public class CountryDaoHibernate extends GenericDaoHibernate<Country, Integer> implements CountryDao {

	public CountryDaoHibernate() {
		super(Country.class);
	}

	@Override
	public List<Country> getByName(String name) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name);
		return findByNamedQuery("Country.getByName", queryParams);
	}

}
