package com.fm.template.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.dao.UserDAO;
import com.fm.template.manager.UserManager;
import com.fm.template.model.User;
import com.fm.template.util.AdminConstants;

@Service(value="userManager")
public class UserManagerImpl extends GenericManagerImpl<User, Integer> implements UserManager, UserDetailsService {
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManagerImpl(UserDAO userDAO) {
		super();
		this.dao = userDAO;
		this.userDAO = userDAO;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserDetails user = filter("username", username);
		if(user == null) {
			throw new UsernameNotFoundException("User with username [" + username + "] not found");
		} else {
			return user;
		}
	}

	@Transactional
	@Override
	public void reset(User user) {
		user.setPassword(passwordEncoder.encodePassword(AdminConstants.DEFAULT_NEW_USERS_PASSWORD, null));
		update(user, false);
	}

}
