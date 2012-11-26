package com.fm.template.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fm.template.manager.CityManager;
import com.fm.template.model.City;

@Component(value = "cityEditor")
public class CityEditor extends PropertyEditorSupport {
	@Autowired
	private CityManager cityManager;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(cityManager.get(Integer.parseInt(text)));
	}

	@Override
	public String getAsText() {
		return ((City) getValue()).getId().toString();
	}

}
