package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.ConfigDAO;
import com.fm.template.model.Config;

@Repository(value="configDAO")
public class ConfigDAOImpl extends GenericDAOImpl<Config, Integer> implements ConfigDAO {

	public ConfigDAOImpl() {
		super(Config.class);
	}

}
