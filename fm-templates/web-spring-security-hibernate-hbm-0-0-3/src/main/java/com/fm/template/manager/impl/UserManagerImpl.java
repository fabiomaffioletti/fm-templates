package com.fm.template.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.dao.UserDAO;
import com.fm.template.exception.UserAlreadyExistingException;
import com.fm.template.exception.UserNotFoundException;
import com.fm.template.manager.UserManager;
import com.fm.template.model.User;

public class UserManagerImpl implements UserManager, UserDetailsService {
	private Logger log = Logger.getLogger(UserManagerImpl.class);
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = userDAO.getUserByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("User with username [" + username + "] not found.");
		}
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundException {
		User user = userDAO.getUserById(id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User with user id [" + id + "] not found");
		}
	}

	@Override
	public Integer saveUser(User user) throws UserAlreadyExistingException {
		try {
			Integer id = userDAO.saveUser(user);
			return id;
		} catch (DataAccessException e) {
			log.warn("User duplicated. Username [" + user.getUsername() + "] Email [" + user.getEmail() + "]");
			throw new UserAlreadyExistingException(e);
		}
	}

	@Override
	public void updateUser(User user) throws UserAlreadyExistingException {
		try {
			userDAO.updateUser(user);
		} catch (DataAccessException e) {
			log.warn("User duplicated. Username [" + user.getUsername() + "] Email [" + user.getEmail() + "]");
			throw new UserAlreadyExistingException(e);
		}
	}

}