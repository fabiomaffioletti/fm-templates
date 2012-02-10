package com.fm.template.dao;

import java.util.List;

import com.fm.template.model.Role;

public interface RoleDAO {
	
	List<Role> getRoles();
	
	Role getRoleById(Integer id);

}