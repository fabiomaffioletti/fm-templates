package com.fm.template.dao.impl;

import org.springframework.stereotype.Repository;

import com.fm.template.dao.RoleDAO;
import com.fm.template.model.Role;

@Repository(value="roleDAO")
public class RoleDAOImpl extends GenericDAOImpl<Role, Integer> implements RoleDAO {

	public RoleDAOImpl() {
		super(Role.class);
	}

}
