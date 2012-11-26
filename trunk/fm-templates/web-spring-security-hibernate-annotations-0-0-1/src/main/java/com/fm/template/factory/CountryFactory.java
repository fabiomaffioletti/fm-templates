package com.fm.template.factory;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fm.template.form.CountryForm;
import com.fm.template.model.Country;

public class CountryFactory {
	
	public static CountryForm toForm(Country country) throws IllegalAccessException, InvocationTargetException {
		CountryForm countryForm = new CountryForm();
		BeanUtils.copyProperties(countryForm, country);
		return countryForm;
	}

}