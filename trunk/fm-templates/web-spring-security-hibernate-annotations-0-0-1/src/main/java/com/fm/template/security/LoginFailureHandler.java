package com.fm.template.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fm.template.manager.UserManager;
import com.fm.template.model.User;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private final Logger log = Logger.getLogger(LoginFailureHandler.class);
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		User user = null;
		try {
			user = (User) userManager.loadUserByUsername(request.getParameter("j_username"));
			user.getUserLoginStatistic().setLastLoginKo(new Date());
			user.getUserLoginStatistic().setLoginKo(user.getUserLoginStatistic().getLoginKo() + 1);
			user.getUserLoginStatistic().setLastLoginKoIPAddress(request.getRemoteAddr());
			userManager.update(user, true);
			//FIXME fabio BUG in user action manager NPE

		} catch (UsernameNotFoundException e) {
			// do nothing
		} catch (DataAccessException e) {
			log.error(e);
		}

		super.onAuthenticationFailure(request, response, exception);
	}

}
