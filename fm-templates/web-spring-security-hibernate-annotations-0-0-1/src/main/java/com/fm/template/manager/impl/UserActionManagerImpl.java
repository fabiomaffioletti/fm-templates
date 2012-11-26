package com.fm.template.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.template.dao.UserActionDAO;
import com.fm.template.manager.UserActionManager;
import com.fm.template.model.User;
import com.fm.template.model.UserAction;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.SecurityUtils;

@Service(value = "userActionManager")
public class UserActionManagerImpl extends GenericManagerImpl<UserAction, Integer> implements UserActionManager {
	@Autowired
	private UserActionDAO userActionDAO;

	@Autowired
	public UserActionManagerImpl(UserActionDAO userActionDAO) {
		super();
		this.dao = userActionDAO;
		this.userActionDAO = userActionDAO;
	}

	@Override
	public void save(User performedOn, String actionDescription) {
		User user = SecurityUtils.getUserFromSecurityContext();
		UserAction userAction = new UserAction();
		userAction.setActionDescription(actionDescription);
		userAction.setPerformedBy(user.getId());
		userAction.setPerformedByUsername(user.getUsername());
		userAction.setPerformedOn(performedOn.getId());
		userAction.setPerformedOnUsername(performedOn.getUsername());
		userAction.setPerformedAt(new Date());
		
		appendBaseObjectData(userAction);
		userActionDAO.save(userAction);
	}

	private void appendBaseObjectData(UserAction userAction) {
		userAction.setCreatedAt(new Date());
		userAction.setCreatedBy(AdminConstants.SYSTEM_USERNAME);
		userAction.setModifiedAt(new Date());
		userAction.setModifiedBy(AdminConstants.SYSTEM_USERNAME);
	}

}
