package com.fm.template.multitenancy.dao;

import java.util.List;

import com.fm.template.multitenancy.model.User;

public interface UserDAO {

	List<User> all();
	
	void save(User user);
	
	void delete(User user);

}
