package com.fm.template.manager;

import com.fm.template.model.User;
import com.fm.template.model.UserAction;

public interface UserActionManager extends GenericManager<UserAction, Integer> {
	
	void save(User performedOn, String actionDescription);

}
