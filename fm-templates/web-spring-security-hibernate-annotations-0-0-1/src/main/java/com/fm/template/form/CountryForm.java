package com.fm.template.form;

import java.util.Set;

public class CountryForm {

	private Integer id;
	private String name;
	private String description;
	private Set<CityForm> cities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<CityForm> getCities() {
		return cities;
	}

	public void setCities(Set<CityForm> cities) {
		this.cities = cities;
	}

}
