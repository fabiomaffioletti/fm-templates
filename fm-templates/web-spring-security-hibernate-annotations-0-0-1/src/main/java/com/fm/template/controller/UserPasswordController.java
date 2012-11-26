package com.fm.template.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.form.UserProfilePassword;
import com.fm.template.manager.UserManager;
import com.fm.template.model.User;
import com.fm.template.util.RequestUtils;
import com.fm.template.util.RequestUtils.MessageType;
import com.fm.template.util.SecurityUtils;

@Controller
public class UserPasswordController extends BaseController implements MessageSourceAware {
	@Autowired
	private UserManager userManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String FORM_VIEW = "user-profile-password";
	private static final String SUCCESS_VIEW = "user-profile-password.html";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "user-profile-password.html", method = RequestMethod.GET)
	public ModelAndView userProfilePassword(Map model) throws IllegalAccessException, InvocationTargetException {
		UserProfilePassword userProfilePassword = new UserProfilePassword();
		BeanUtils.copyProperties(userProfilePassword, SecurityUtils.getUserFromSecurityContext());
		model.put("userProfilePassword", userProfilePassword);
		return new ModelAndView(FORM_VIEW);
	}

	@RequestMapping(value = "user-profile-password.html", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute(value = "userProfilePassword") @Valid UserProfilePassword userProfilePassword, BindingResult result, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		if (result.hasErrors()) {
			return FORM_VIEW;
		}
		
		try {
			User logged = SecurityUtils.getUserFromSecurityContext();
			String oldPassword = logged.getPassword();
			if(!oldPassword.equals(passwordEncoder.encodePassword(userProfilePassword.getPassword(), null))) {
				RequestUtils.message(MessageType.ERROR, messageSource, "user.profile.old.password.match", null, request);
				return FORM_VIEW;
				
			} else if(!userProfilePassword.getNewPassword().equals(userProfilePassword.getConfirmNewPassword())) {
				RequestUtils.message(MessageType.ERROR, messageSource, "user.profile.new.passwords.match", null, request);
				return FORM_VIEW;
				
			} else {
				logged.setPassword(passwordEncoder.encodePassword(userProfilePassword.getNewPassword(), null));
				userManager.merge(logged, false);
				RequestUtils.message(MessageType.SUCCESS, messageSource, "user.profile.password.updated", null, request);
				return "redirect:" + SUCCESS_VIEW;
			}
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return "redirect:" + SUCCESS_VIEW;
		}
	}

}