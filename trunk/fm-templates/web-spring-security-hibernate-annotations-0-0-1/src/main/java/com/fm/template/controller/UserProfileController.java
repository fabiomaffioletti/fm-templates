package com.fm.template.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.dto.UserProfile;
import com.fm.template.util.SecurityUtils;

@Controller
public class UserProfileController extends BaseController implements MessageSourceAware {
	private static final String SUCCESS_VIEW = "user-profile";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "user-profile.html", method = RequestMethod.GET)
	public ModelAndView userProfile(Map model) throws IllegalAccessException, InvocationTargetException {
		UserProfile userProfile = new UserProfile();
		BeanUtils.copyProperties(userProfile, SecurityUtils.getUserFromSecurityContext());
		model.put("userProfile", userProfile);
		return new ModelAndView(SUCCESS_VIEW);
	}

}