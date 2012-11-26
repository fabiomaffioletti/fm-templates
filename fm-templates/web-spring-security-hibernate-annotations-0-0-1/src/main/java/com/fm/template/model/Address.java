package com.fm.template.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Address {

	@Column(name = "street")
	@Length(max = 255, message = "{user.street.length}")
	private String street;
	@Column(name = "number")
	@Length(max = 45, message = "{user.number.length}")
	private String number;
	@Column(name = "zip_code")
	@Digits(fraction = 0, integer = 5, message = "{user.zipCode.digits}")
	private Integer zipCode;
	@Column(name = "city")
	@Length(max = 100, message = "{user.city.length}")
	private String city;
	@Column(name = "state")
	@Length(max = 100, message = "{user.state.length}")
	private String state;
	@Column(name = "country")
	@Length(max = 100, message = "{user.country.length}")
	private String country;

	public Address() {

	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
