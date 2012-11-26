package com.fm.template.dto;

import com.fm.template.model.Address;
import com.fm.template.model.UserLoginStatistic;

public class UserProfile {

	private Integer id;
	private String username;
	private String email;
	private Address address;
	private UserLoginStatistic userLoginStatistic;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserLoginStatistic getUserLoginStatistic() {
		return userLoginStatistic;
	}

	public void setUserLoginStatistic(UserLoginStatistic userLoginStatistic) {
		this.userLoginStatistic = userLoginStatistic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
