package com.fm.template.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.dao.ConfigDAO;
import com.fm.template.manager.ConfigManager;
import com.fm.template.model.Config;

@Service(value="configManager")
public class ConfigManagerImpl extends GenericManagerImpl<Config, Integer> implements ConfigManager {
	private ConfigDAO configDAO;
	
	@Autowired
	public ConfigManagerImpl(ConfigDAO configDAO) {
		super();
		this.dao = configDAO;
		this.configDAO = configDAO;
	}

}
