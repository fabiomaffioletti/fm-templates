package com.fm.template.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.model.User;

public interface UserDAO {
	
	User getUserByUsername(String username) throws UsernameNotFoundException;

}