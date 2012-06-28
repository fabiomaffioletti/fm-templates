package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.CityDao;
import com.fm.template.model.City;

@Repository(value="cityDao")
public class CityDaoHibernate extends GenericDaoHibernate<City, Integer> implements CityDao {

	public CityDaoHibernate() {
		super(City.class);
	}

}
