package com.fm.template.manager;

import java.util.List;

import com.fm.template.model.Role;

public interface RoleManager {

	List<Role> getRoles();
	
	Role getRoleById(Integer id);
}