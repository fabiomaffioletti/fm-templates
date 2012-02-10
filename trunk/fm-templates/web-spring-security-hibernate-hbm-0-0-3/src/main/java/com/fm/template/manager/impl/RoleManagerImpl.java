package com.fm.template.manager.impl;

import java.util.List;

import com.fm.template.dao.RoleDAO;
import com.fm.template.manager.RoleManager;
import com.fm.template.model.Role;

public class RoleManagerImpl implements RoleManager {
	private RoleDAO roleDAO;

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleDAO.getRoleById(id);
	}
	
	

}