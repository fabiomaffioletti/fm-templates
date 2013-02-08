package com.lumata.multitenancy.dao;

import java.util.List;

import com.lumata.multitenancy.model.User;

public interface UserDAO {

	List<User> all();
	
	void save(User user);
	
	void delete(User user);

	void saveRollback(User user);
	
	User getByName(String name);

}
