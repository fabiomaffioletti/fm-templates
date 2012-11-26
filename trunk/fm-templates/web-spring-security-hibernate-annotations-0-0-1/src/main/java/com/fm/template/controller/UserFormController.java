package com.fm.template.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.editor.RoleEditor;
import com.fm.template.manager.RoleManager;
import com.fm.template.manager.UserManager;
import com.fm.template.model.Role;
import com.fm.template.model.User;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.RequestUtils;
import com.fm.template.util.RequestUtils.MessageType;

@Controller
public class UserFormController extends BaseController implements MessageSourceAware {
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private RoleEditor roleEditor;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String FORM_VIEW = "admin/admin-edit-user";
	private static final String SUCCESS_VIEW = "admin-list-users.html";
	private static final String REDIRECT_FORM_VIEW = "admin-edit-user.html?id=";

	@ModelAttribute(value = "roles")
	public List<Role> roles() {
		return roleManager.all();
	}

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Role.class, roleEditor);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "admin-edit-user.html", method = RequestMethod.GET)
	public ModelAndView user(Integer id, Map model) {
		if (id == null) {
			model.put("user", new User());
		} else {
			model.put("user", userManager.get(id));
		}
		return new ModelAndView(FORM_VIEW);
	}

	@RequestMapping(value = "admin-edit-user.html", method = RequestMethod.POST)
	public String save(@ModelAttribute(value = "user") @Valid User user, BindingResult result, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		if (result.hasErrors()) {
			return FORM_VIEW;
		}
		
		try {
			
			if (user.getId() != null) {
				userManager.merge(user, false);
				RequestUtils.message(MessageType.SUCCESS, messageSource, "user.updated", new Object[] { user.getUsername() }, request);

			} else {
				user.setPassword(passwordEncoder.encodePassword(AdminConstants.DEFAULT_NEW_USERS_PASSWORD, null));
				userManager.save(user, false);
				RequestUtils.message(MessageType.SUCCESS, messageSource, "user.saved", new Object[] { user.getUsername() }, request);
			}

			return "redirect:"+REDIRECT_FORM_VIEW+user.getId();
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return "redirect:" + SUCCESS_VIEW;
		}
	}

	@RequestMapping(value = "admin-delete-user.html", method = RequestMethod.GET)
	public String delete(Integer id, HttpServletRequest request) {
		try {
			userManager.remove(userManager.get(id));
			RequestUtils.message(MessageType.SUCCESS, messageSource, "user.deleted", null, request);
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
		}
		return "redirect:" + SUCCESS_VIEW;
	}

	@RequestMapping(value = "admin-reset-user.html", method = RequestMethod.GET)
	public String reset(Integer id, HttpServletRequest request) {
		try {
			userManager.reset(userManager.get(id));
			RequestUtils.message(MessageType.SUCCESS, messageSource, "user.reset", null, request);
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
		}
		return "redirect:"+REDIRECT_FORM_VIEW+id;
	}

}