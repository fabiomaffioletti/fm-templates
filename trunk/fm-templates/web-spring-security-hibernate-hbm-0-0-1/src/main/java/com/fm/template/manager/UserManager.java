package com.fm.template.manager;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserManager {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
	
}