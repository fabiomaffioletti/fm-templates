package com.fm.template.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fm.template.manager.ConfigManager;
import com.fm.template.manager.UserActionManager;
import com.fm.template.model.User;
import com.fm.template.util.AdminConstants;

@Component
@Aspect
public class UserActionAspect {
	@Autowired
	private UserActionManager userActionManager;
	@Autowired
	private ConfigManager configManager;

	@Pointcut(value = "execution(* com.fm.template.manager.GenericManager.save*(..)) && args(user,..)")
	public void save(User user) {

	}

	@Pointcut(value = "execution(* com.fm.template.manager.GenericManager.update*(..)) && args(user,..)")
	public void update(User user) {

	}

	@Pointcut(value = "execution(* com.fm.template.manager.GenericManager.merge*(..)) && args(user,..)")
	public void merge(User user) {

	}
	
	@Pointcut(value = "execution(* com.fm.template.manager.GenericManager.remove*(..)) && args(user,..)")
	public void remove(User user) {

	}
	
	@Pointcut(value = "execution(* com.fm.template.manager.UserManager.reset*(..)) && args(user,..)")
	public void reset(User user) {

	}
	
	@Around(value = "save(user) || update(user) || merge(user) || remove(user) || reset(user)")
	public Object actionLogger1(ProceedingJoinPoint jp, User user) throws Throwable {
		if(configManager.filter(AdminConstants.CONFIG_NAME, AdminConstants.USER_ACTIONS_LOG_ENABLED).getConfigValue().equals("true")) {
			userActionManager.save(user, jp.getSignature().getName());
		}
		return jp.proceed();
	}
	
}