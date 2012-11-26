package com.fm.template.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fm.template.manager.RoleManager;
import com.fm.template.model.Role;

@Component(value = "roleEditor")
public class RoleEditor extends PropertyEditorSupport {
	@Autowired
	private RoleManager roleManager;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(roleManager.get(Integer.parseInt(text)));
	}

	@Override
	public String getAsText() {
		return ((Role) getValue()).getId().toString();
	}

}
