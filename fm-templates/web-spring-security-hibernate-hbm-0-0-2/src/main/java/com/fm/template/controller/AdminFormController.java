package com.fm.template.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
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
import com.fm.template.exception.UserAlreadyExistingException;
import com.fm.template.exception.UserNotFoundException;
import com.fm.template.manager.RoleManager;
import com.fm.template.manager.UserManager;
import com.fm.template.model.Role;
import com.fm.template.model.User;

@Controller
public class AdminFormController extends BaseFormController implements MessageSourceAware {
	private static Logger log = Logger.getLogger(AdminController.class);
	private UserManager userManager;
	private RoleManager roleManager;
	private RoleEditor roleEditor;
	private MessageSource messageSource;
	private PasswordEncoder passwordEncoder;
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	
	public void setRoleEditor(RoleEditor roleEditor) {
		this.roleEditor = roleEditor;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Role.class, roleEditor);
	}

	@ModelAttribute(value="allRoles")
	public List<Role> allRoles() {
		return roleManager.getRoles();
	}

	@RequestMapping(value="/admin-user.html", method=RequestMethod.GET)
	public ModelAndView getUser(Integer id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(getFormView());
		
		if(id == null) {
			mv.addObject("user", new User(passwordEncoder.encodePassword("user", null)));
			
		} else {
			try {
				User user = userManager.getUserById(id);
				mv.setViewName(getFormView());
				mv.addObject("user", user);
				
			} catch (UserNotFoundException e) {
				request.getSession().setAttribute("infoMessage", messageSource.getMessage("admin.item.not.found", null, request.getLocale()));
				mv.addObject("user", new User());
				log.warn(e.getMessage(), e);
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/admin-user.html", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute(value="user") @Valid User user, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			return getFormView();
		}
		
		try {
			if(user.getId() == null) {
				user.setId(userManager.saveUser(user));
				
			} else {
				userManager.updateUser(user);
			}
			
		} catch (UserAlreadyExistingException e) {
			request.getSession().setAttribute("infoMessage", messageSource.getMessage("user.duplicate", null, request.getLocale()));
			return getFormView();
		}
		
		request.getSession().setAttribute("successMessage", messageSource.getMessage("admin.item.saved", null, request.getLocale()));
		return getSuccessView()+user.getId();
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
