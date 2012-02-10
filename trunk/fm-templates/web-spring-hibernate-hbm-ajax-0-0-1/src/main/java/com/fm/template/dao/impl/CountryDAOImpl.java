package com.fm.template.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fm.template.dao.CountryDAO;
import com.fm.template.model.Country;

public class CountryDAOImpl extends HibernateDaoSupport implements CountryDAO {

	@Override
	public List<Country> getCountries() {
		return getHibernateTemplate().loadAll(Country.class);
	}

	@Override
	public Country getCountryById(Integer id) {
		return getHibernateTemplate().get(Country.class, id);
	}

}
