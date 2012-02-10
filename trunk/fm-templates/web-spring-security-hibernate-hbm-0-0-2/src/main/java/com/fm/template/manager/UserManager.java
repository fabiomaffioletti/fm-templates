package com.fm.template.manager;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.exception.UserAlreadyExistingException;
import com.fm.template.exception.UserNotFoundException;
import com.fm.template.model.User;

public interface UserManager {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
	
	List<User> getUsers();
	
	User getUserById(Integer id) throws UserNotFoundException;
	
	Integer saveUser(User user) throws UserAlreadyExistingException;
	
	void updateUser(User user) throws UserAlreadyExistingException;
	
}