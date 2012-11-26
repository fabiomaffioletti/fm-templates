package com.fm.template.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.factory.ConfigFactory;
import com.fm.template.form.ConfigForm;
import com.fm.template.manager.ConfigManager;
import com.fm.template.model.Config;
import com.fm.template.util.RequestUtils;
import com.fm.template.util.RequestUtils.MessageType;

@Controller
public class ConfigFormController extends BaseController {
	private static final String FORM_VIEW = "admin/admin-edit-config";
	private static final String SUCCESS_VIEW = "admin-list-configs.html";
	private static final String REDIRECT_FORM_VIEW = "admin-edit-config.html?id=";

	@Autowired
	private ConfigManager configManager;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "admin-edit-config.html", method = RequestMethod.GET)
	public ModelAndView config(Integer id, HttpServletRequest request, Map model) {
		try {
			if(id == null) {
				model.put("configForm", new ConfigForm());
			} else {
				model.put("configForm", ConfigFactory.toForm(configManager.get(id)));
			}
			return new ModelAndView(FORM_VIEW);
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return new ModelAndView(FORM_VIEW);
		}
	}
	
	@RequestMapping(value = "admin-edit-config.html", method = RequestMethod.POST)
	public String save(@ModelAttribute(value = "configForm") @Valid ConfigForm configForm, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return FORM_VIEW;
		}
		
		try {
			Config config = null;
			if(configForm.getId() == null) {
				config = new Config();
			} else {
				config = configManager.get(configForm.getId());
			}
			config = configManager.merge(ConfigFactory.fromForm(configForm, config), false);
			RequestUtils.message(MessageType.SUCCESS, messageSource, "item.updated", null, request);
			
			return "redirect:"+REDIRECT_FORM_VIEW+config.getId();
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return "redirect:" + SUCCESS_VIEW;
		}
		
	}
	
}
