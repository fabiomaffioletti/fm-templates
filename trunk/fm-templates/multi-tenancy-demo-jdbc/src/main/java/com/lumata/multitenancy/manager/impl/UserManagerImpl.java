package com.lumata.multitenancy.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumata.multitenancy.dao.UserDAO;
import com.lumata.multitenancy.manager.UserManager;
import com.lumata.multitenancy.model.User;
import com.lumata.multitenancy.routing.MultiTenancyServer;

@Service("userManager")
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> all(MultiTenancyServer multiTenancyServer) {
		return userDAO.all();
	}

	@Override
	public void save(MultiTenancyServer multiTenancyServer, User user) {
		userDAO.save(user);
	}

	@Override
	public void delete(MultiTenancyServer multiTenancyServer, User user) {
		userDAO.delete(user);
	}

	@Override
	public User getByName(MultiTenancyServer multiTenancyServer, String name) {
		return userDAO.getByName(name);
	}

}