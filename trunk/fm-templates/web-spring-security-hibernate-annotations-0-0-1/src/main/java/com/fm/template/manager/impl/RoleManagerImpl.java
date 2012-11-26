package com.fm.template.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.dao.RoleDAO;
import com.fm.template.manager.RoleManager;
import com.fm.template.model.Role;

@Service(value="roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Integer> implements RoleManager {
	private RoleDAO roleDAO;
	
	@Autowired
	public RoleManagerImpl(RoleDAO roleDAO) {
		super();
		this.dao = roleDAO;
		this.roleDAO = roleDAO;
	}

}
