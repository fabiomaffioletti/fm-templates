package com.fm.template.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fm.template.dao.UserDAO;
import com.fm.template.model.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		List<User> users = getHibernateTemplate().findByNamedQueryAndNamedParam("User.getUserByUsername", "username", username);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	public List<User> getUsers() {
		return getHibernateTemplate().loadAll(User.class);
	}

	@Override
	public User getUserById(Integer id) {
		return getHibernateTemplate().get(User.class, id);
	}

	@Override
	public Integer saveUser(User user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

}