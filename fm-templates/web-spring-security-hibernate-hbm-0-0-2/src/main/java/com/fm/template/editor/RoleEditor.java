package com.fm.template.editor;

import java.beans.PropertyEditorSupport;

import com.fm.template.dao.RoleDAO;
import com.fm.template.model.Role;

public class RoleEditor extends PropertyEditorSupport {
	private RoleDAO roleDAO;
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(roleDAO.getRoleById(Integer.parseInt(text)));
	}
	
	@Override
	public String getAsText() {
		return ((Role) getValue()).getId().toString();
	}

}
