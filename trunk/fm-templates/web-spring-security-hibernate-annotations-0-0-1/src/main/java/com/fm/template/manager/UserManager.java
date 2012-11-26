package com.fm.template.manager;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.model.User;

public interface UserManager extends GenericManager<User, Integer> {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
	
	void reset(User user);
	
}
