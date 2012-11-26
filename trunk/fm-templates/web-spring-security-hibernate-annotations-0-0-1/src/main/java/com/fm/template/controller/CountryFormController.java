package com.fm.template.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.editor.CityEditor;
import com.fm.template.manager.CityManager;
import com.fm.template.manager.CountryManager;
import com.fm.template.model.City;
import com.fm.template.model.Country;
import com.fm.template.util.RequestUtils;
import com.fm.template.util.RequestUtils.MessageType;

@Controller
public class CountryFormController extends BaseController {
	private static final String FORM_VIEW = "admin/admin-edit-country";
	private static final String SUCCESS_VIEW = "admin-list-countries.html";
	private static final String REDIRECT_FORM_VIEW = "admin-edit-country.html?id=";

	@Autowired
	private CountryManager countryManager;
	@Autowired
	private CityManager cityManager;
	@Autowired
	private CityEditor cityEditor;
//	@Autowired
//	private RoleManager roleManager;
//	@Autowired
//	private RoleEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(City.class, cityEditor);
//		b.registerCustomEditor(Role.class, roleEditor);
	}
	
	@ModelAttribute(value = "allCities")
	public List<City> cities() {
		return cityManager.all();
	}
	
//	@ModelAttribute(value = "allRoles")
//	public List<Role> roles() {
//		return roleManager.all();
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "admin-edit-country.html", method = RequestMethod.GET)
	public ModelAndView country(Integer id, HttpServletRequest request, Map model) {
		try {
			if(id == null) {
				model.put("country", new Country());
			} else {
				model.put("country", countryManager.get(id));
			}
			return new ModelAndView(FORM_VIEW);
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return new ModelAndView(FORM_VIEW);
		}
	}
	
	@RequestMapping(value = "admin-edit-country.html", method = RequestMethod.POST)
	public String save(@ModelAttribute(value = "country") @Valid Country country, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return FORM_VIEW;
		}
		
		try {
			if(country.getId() == null) {
				country.setId(countryManager.save(country, true));
			} else {
				countryManager.merge(country, true);
			}
			
			return "redirect:"+REDIRECT_FORM_VIEW+country.getId();
			
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
			return "redirect:" + SUCCESS_VIEW;
		}
		
	}
	
}
