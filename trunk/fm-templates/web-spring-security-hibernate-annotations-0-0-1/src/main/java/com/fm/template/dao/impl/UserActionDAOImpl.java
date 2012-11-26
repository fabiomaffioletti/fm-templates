package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.UserActionDAO;
import com.fm.template.model.UserAction;

@Repository(value="userActionDAO")
public class UserActionDAOImpl extends GenericDAOImpl<UserAction, Integer> implements UserActionDAO {

	public UserActionDAOImpl() {
		super(UserAction.class);
	}

}
