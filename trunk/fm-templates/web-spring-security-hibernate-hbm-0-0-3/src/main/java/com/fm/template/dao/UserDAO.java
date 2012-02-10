package com.fm.template.dao;

import java.util.List;

import com.fm.template.model.User;

public interface UserDAO {
	
	User getUserByUsername(String username);
	
	List<User> getUsers();
	
	User getUserById(Integer id);
	
	Integer saveUser(User user);
	
	void updateUser(User user);

}