package com.fm.template.multitenancy.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.multitenancy.dao.UserDAO;
import com.fm.template.multitenancy.model.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> all() {
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	@Override
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

}
