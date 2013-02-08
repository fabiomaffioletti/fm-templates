package com.fm.template.multitenancy.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.multitenancy.dao.UserDAO;
import com.fm.template.multitenancy.manager.UserManager;
import com.fm.template.multitenancy.model.User;
import com.fm.template.multitenancy.routing.MultiTenancyServer;

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