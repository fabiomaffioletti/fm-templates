package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.UserDAO;
import com.fm.template.model.User;

@Repository(value="userDAO")
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

}
