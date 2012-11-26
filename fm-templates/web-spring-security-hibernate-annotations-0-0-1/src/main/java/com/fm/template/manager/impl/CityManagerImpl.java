package com.fm.template.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.dao.CityDAO;
import com.fm.template.manager.CityManager;
import com.fm.template.model.City;

@Service(value="cityManager")
public class CityManagerImpl extends GenericManagerImpl<City, Integer> implements CityManager {
	private CityDAO cityDAO;
	
	@Autowired
	public CityManagerImpl(CityDAO cityDAO) {
		super();
		this.dao = cityDAO;
		this.cityDAO = cityDAO;
	}

}
