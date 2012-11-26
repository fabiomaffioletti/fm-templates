package com.fm.template.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.manager.ConfigManager;

@Controller
public class ConfigController extends BaseController {
	@Autowired
	private ConfigManager configManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "admin-list-configs.html", method = RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request, Map model) {
		model.put("configs", configManager.all());
		return new ModelAndView("admin/admin-list-configs");
	}
	
}