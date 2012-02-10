package com.fm.template.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
	
}