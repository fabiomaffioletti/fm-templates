package com.lumata.multitenancy.model;

public class User {
	private Integer id;
	private String name;

	public User() {

	}

	public User(String name) {
		super();
		this.name = name;
	}

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "User [" + name + "]";
	}

}
