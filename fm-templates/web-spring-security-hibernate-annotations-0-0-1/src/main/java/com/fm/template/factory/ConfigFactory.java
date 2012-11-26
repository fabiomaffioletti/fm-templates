package com.fm.template.factory;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fm.template.form.ConfigForm;
import com.fm.template.model.Config;

public class ConfigFactory {
	
	public static ConfigForm toForm(Config config) throws IllegalAccessException, InvocationTargetException {
		ConfigForm configForm = new ConfigForm();
		BeanUtils.copyProperties(configForm, config);
		return configForm;
	}
	
	public static Config fromForm(ConfigForm configForm, Config config) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(config, configForm);
		return config;
	}

}
