package com.fm.template.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.dao.UserDAO;
import com.fm.template.model.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = getHibernateTemplate().findByNamedQueryAndNamedParam("User.getUserByUsername", "username", username);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			throw new UsernameNotFoundException("User with username [" + username + "] not found.");
		}
	}

}