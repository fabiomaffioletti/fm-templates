package com.fm.template.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fm.template.manager.UserManager;
import com.fm.template.model.User;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final Logger log = Logger.getLogger(LoginSuccessHandler.class);
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		user.getUserLoginStatistic().setLoginOk(user.getUserLoginStatistic().getLoginOk() + 1);
		user.getUserLoginStatistic().setLastLoginOk(new Date());
		user.getUserLoginStatistic().setLastLoginOkIPAddress(request.getRemoteAddr());
		userManager.update(user, true);

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

}
