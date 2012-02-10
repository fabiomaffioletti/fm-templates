package com.fm.template.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fm.template.model.User;

public class Utils {
	
	public static User getUserFromSecurityContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return (User) authentication.getPrincipal();
		} else {
			return null;
		}
	}

}
