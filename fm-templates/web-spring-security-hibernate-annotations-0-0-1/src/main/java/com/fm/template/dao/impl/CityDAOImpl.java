package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.CityDAO;
import com.fm.template.model.City;

@Repository(value="cityDAO")
public class CityDAOImpl extends GenericDAOImpl<City, Integer> implements CityDAO {

	public CityDAOImpl() {
		super(City.class);
	}

}
