package com.fm.template.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.manager.UserManager;
import com.fm.template.model.User;

@Controller
public class AdminController extends BaseController {
	private UserManager userManager;
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@ModelAttribute(value="users")
	public List<User> users() {
        return userManager.getUsers();
    }

	@RequestMapping(value="/admin.html", method=RequestMethod.GET)
	public ModelAndView getUsers() {
		return new ModelAndView(getControllerView());
	}

}
